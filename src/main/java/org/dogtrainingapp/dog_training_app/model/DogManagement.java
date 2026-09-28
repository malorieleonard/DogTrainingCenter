package org.dogtrainingapp.dog_training_app.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Formatter;
import java.util.stream.Collectors;

/**
 * Manages all dog and training record operations for the Dog Training Center.
 * Provides CRUD operations, search functionality, and file I/O persistence.
 *
 * <p>Data is stored in two separate files under the "data" directory:</p>
 * <ul>
 *   <li>{@code data/dogs.txt} — stores dog information (read/written with Scanner/Formatter)</li>
 *   <li>{@code data/records.txt} — stores training records (read/written with BufferedReader/BufferedWriter)</li>
 * </ul>
 *
 * <p>File formats:</p>
 * <pre>
 *   dogs.txt:    dogID,TYPE,name,age,breed
 *   records.txt: dogID,skill,date,PERFORMANCE
 * </pre>
 */
public class DogManagement {

    private List<Dog> dogs;

    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DOG_FILE = Paths.get("data", "dogs.txt");
    private static final Path RECORD_FILE = Paths.get("data", "records.txt");

    /**
     * Constructs a new DogManagement instance.
     * Initializes the dogs list and ensures the data directory exists.
     */
    public DogManagement() {
        dogs = new ArrayList<>();

        try {
            if (!Files.exists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the list of all dogs currently managed.
     *
     * @return the list of {@link Dog} objects
     */
    public List<Dog> getDogs() {
        return dogs;
    }

    /**
     * Adds a new dog to the managed list.
     *
     * @param dog the {@link Dog} to add
     */
    public void addDog(Dog dog) {
        dogs.add(dog);
    }

    /**
     * Searches for a dog by its unique ID.
     *
     * @param id the dog ID to search for
     * @return the {@link Dog} with the matching ID, or {@code null} if not found
     */
    public Dog searchByID(int id) {
        for (Dog d : dogs) {
            if (d.getDogID() == id) {
                return d;
            }
        }
        return null;
    }

    /**
     * Searches for dogs by name (case-insensitive).
     *
     * @param name the name to search for
     * @return a list of {@link Dog} objects matching the name
     */
    public List<Dog> searchByName(String name) {
        return dogs.stream()
                .filter(d -> d.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    /**
     * Searches for dogs by their type.
     *
     * @param type the {@link DogType} to filter by
     * @return a list of {@link Dog} objects matching the type
     */
    public List<Dog> searchByType(DogType type) {
        return dogs.stream()
                .filter(d -> d.getType() == type)
                .collect(Collectors.toList());
    }

    /**
     * Saves all dogs to data/dogs.txt.
     * Format per line: dogID,TYPE,name,age,breed
     */
    public void saveDogs() {
        try (Formatter fmt = new Formatter(DOG_FILE.toFile())) {
            for (Dog d : dogs) {
                fmt.format("%d,%s,%s,%d,%s%n",
                        d.getDogID(),
                        d.getType().name(),
                        d.getName(),
                        d.getAge(),
                        d.getBreed());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads dogs from data/dogs.txt.
     */
    public void loadDogs() {
        dogs.clear();

        if (!Files.exists(DOG_FILE)) {
            return;
        }

        try (Scanner scanner = new Scanner(DOG_FILE.toFile())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    DogType type = DogType.valueOf(parts[1]);
                    String name = parts[2];
                    int age = Integer.parseInt(parts[3]);
                    String breed = parts[4];

                    Dog dog = createDog(type, id, name, age, breed);
                    if (dog != null) {
                        dogs.add(dog);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves all training records to data/records.txt.
     * Format per line: dogID,skill,date,PERFORMANCE
     */
    public void saveRecords() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RECORD_FILE.toFile()))) {
            for (Dog d : dogs) {
                for (TrainingRecord r : d.getRecords()) {
                    bw.write(d.getDogID() + "," + r.getSkill() + "," + r.getDate() + "," + r.getPerformance().name());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads training records from data/records.txt and attaches each record to its corresponding Dog.
     */
    public void loadRecords() {
        if (!Files.exists(RECORD_FILE)) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(RECORD_FILE.toFile()))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    int dogID = Integer.parseInt(parts[0]);
                    String skill = parts[1];
                    String date = parts[2];
                    Performance performance = Performance.valueOf(parts[3]);

                    TrainingRecord record = new TrainingRecord(skill, date, performance);
                    Dog dog = searchByID(dogID);

                    if (dog != null) {
                        dog.addRecord(record);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Factory method that creates the appropriate Dog subclass
     * based on the given {@link DogType}.
     *
     * @param type the type of dog to create
     * @param id the unique identifier for the dog
     * @param name the name of the dog
     * @param age the age of the dog
     * @param breed the breed of the dog
     * @return a new {@link TherapyDog}, {@link PoliceDog}, or {@link RescueDog},
     *         or {@code null} if the type is unrecognized
     */
    private Dog createDog(DogType type, int id, String name, int age, String breed) {
        switch (type) {
            case THERAPY:
                return new TherapyDog(id, name, age, breed);
            case POLICE:
                return new PoliceDog(id, name, age, breed);
            case RESCUE:
                return new RescueDog(id, name, age, breed);
            default:
                return null;
        }
    }
}