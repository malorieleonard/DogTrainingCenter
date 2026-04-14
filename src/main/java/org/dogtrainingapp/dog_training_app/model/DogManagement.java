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
 *   dogs.txt:    dogID,TYPE,name,age,breed        (e.g., 1,THERAPY,Izzy,3,Golden Retriever)
 *   records.txt: dogID,skill,date,PERFORMANCE     (e.g., 1,Sit,2026-03-01,EXCELLENT)
 * </pre>
 */
public class DogManagement {

    private List<Dog> dogs;
    //Declare three static final Path constants using Paths.get().
    private static final Path DATA_DIR    = Paths.get("data");
    private static final Path DOG_FILE    = Paths.get("data", "dogs.txt");
    private static final Path RECORD_FILE = Paths.get("data", "records.txt");

    /**
     * Constructs a new DogManagement instance.
     * Initializes the dogs list and ensures the data directory exists.
     */
    public DogManagement() {
        dogs = new ArrayList<>();

        // TODO: Use Files.exists() to check if DATA_DIR exists.
        //       If it does NOT exist, create it using Files.createDirectories().
        //       Wrap in try-catch for IOException.


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
            if (d.getDogID() == id) return d;
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
     * Saves all dogs to data/dogs.txt
     * Format per line:  dogID,TYPE,name,age,breed
     * Example:  1,THERAPY,Izzy,3,Golden Retriever
     */
    public void saveDogs() {
        //TODO: Implement using Formatter to save all dogs to data/dogs.txt
        //      use try-with-resources: Formatter fmt = new Formatter(DOG_FILE.toFile())
        //      Loop through all dogs
        //          use fmt.format() with specifiers: %d,%s,%s,%d,%s%n
        //          use d.getType().name() to get the enum constant name (e.g., "THERAPY" not "Therapy dog")
        //      Wrap in try-catch for IOException.


    }

    /**
     * Loads dogs from data/dogs.txt
     */
    public void loadDogs() {
        dogs.clear();

        //TODO: load dogs from data/dogs.txt using Scanner
        //   First check if DOG_FILE exists using Files.exists()
        //           if it does not exist, return;
        //   Use try-with-resources: new Scanner(DOG_FILE.toFile())
        //   Use scanner.hasNextLine() and scanner.nextLine()
        //      Split each line by "," and parse each part:
        //              parts[0] → dogID   (Integer.parseInt())
        //              parts[1] → type    (DogType.valueOf())
        //              parts[2] → name
        //              parts[3] → age     (Integer.parseInt())
        //              parts[4] → breed
        //      Call createDog(type, id, name, age, breed)
        //      Add the dog to the list dogs if not null
        // Wrap in try-catch for IOException.



    }

    /**
     * Saves all training records to data/records.txt
     * Format per line:  dogID,skill,date,PERFORMANCE
     * Example:          1,Sit,2026-03-01,EXCELLENT
     */
    public void saveRecords() {

        //TODO: Implement using BufferedWriter to save all training records to data/records.txt
        //   Use try-with-resources: new BufferedWriter(new FileWriter(RECORD_FILE.toFile()))
        //   Loop through all dogs, then loop through each dog's records
        //        - Use bw.write() to write: dogID + "," + skill + "," + date + "," + performance
        //        - Use bw.newLine() after each record
        //   Wrap in try-catch for IOException.
        //   NOTE:
        //        get dogID from the Dog (d.getDogID()) since TrainingRecord doesn't have dogID
        //        use r.getPerformance().name() to get the enum constant name
        //                (e.g., "EXCELLENT" not "Excellent")



    }

    /**
     * Loads training records from data/records.txt and attaches each record to its corresponding Dog.
     */
    public void loadRecords() {

        //TODO: Implement using BufferedReader to load training records
        //   Check if RECORD_FILE exists using Files.exists()
        //                if it does not exist, return.
        //   Use try-with-resources: new BufferedReader(new FileReader(RECORD_FILE.toFile()))
        //       Read lines in a while loop: while ((line = br.readLine()) != null)
        //          Split each line by "," and parse each part:
        //              parts[0] → dogID
        //              parts[1] → skill
        //              parts[2] → date
        //              parts[3] → performance (Performance.valueOf())
        //          Create: new TrainingRecord(skill, date, performance)
        //          Use searchByID(dogID) to find the owning Dog
        //          If dog is not null, call dog.addRecord(record)
        //   Wrap in try-catch for IOException.


    }

    /**
     * Factory method that creates the appropriate Dog subclass
     * based on the given {@link DogType}.
     *
     * @param type  the type of dog to create
     * @param id    the unique identifier for the dog
     * @param name  the name of the dog
     * @param age   the age of the dog
     * @param breed the breed of the dog
     * @return a new {@link TherapyDog}, {@link PoliceDog}, or {@link RescueDog},
     *         or {@code null} if the type is unrecognized
     */
    private Dog createDog(DogType type, int id, String name, int age, String breed) {
        switch (type) {
            case THERAPY: return new TherapyDog(id, name, age, breed);
            case POLICE:  return new PoliceDog(id, name, age, breed);
            case RESCUE:  return new RescueDog(id, name, age, breed);
            default:      return null;
        }
    }
}