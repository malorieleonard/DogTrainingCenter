package org.dogtrainingapp.dog_training_app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class representing a dog in the training center.
 * Each dog has an ID, name, age, breed, and a list of training records.
 *
 * <p>Subclasses must implement:</p>
 * <ul>
 *   <li>{@link #speak()} — returns a String representing the dog's vocalization</li>
 *   <li>{@link #getType()} — returns the {@link DogType} of the dog</li>
 * </ul>
 */
public abstract class Dog {
    private int dogID;
    private String name;
    private int age;
    private String breed;
    private List<TrainingRecord> records;

    /**
     * Constructs a new Dog with the specified attributes.
     * Initializes the training records list as an empty ArrayList.
     *
     * @param dogID the unique identifier for this dog
     * @param name  the name of the dog
     * @param age   the age of the dog in years
     * @param breed the breed of the dog
     */
    public Dog(int id, String name, int age, String breed) {
        this.dogID = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.records = new ArrayList<>();
    }

    /**
     * Returns the unique identifier of this dog.
     *
     * @return the dog ID
     */
    public int getDogID() { return dogID; }

    /**
     * Returns the name of this dog.
     *
     * @return the dog's name
     */
    public String getName() { return name; }

    /**
     * Returns the age of this dog.
     *
     * @return the dog's age in years
     */
    public int getAge() {return age; }

    /**
     * Returns the breed of this dog.
     *
     * @return the dog's breed
     */
    public String getBreed() { return breed; }

    /**
     * Returns the list of training records for this dog.
     *
     * @return the list of {@link TrainingRecord} objects
     */
    public List<TrainingRecord> getRecords(){ return records; }

    /**
     * Sets the age of this dog.
     *
     * @param age the new age in years
     */
    public void setAge(int age) { this.age = age; }

    /**
     * Adds a training record to this dog's training history.
     *
     * @param record the {@link TrainingRecord} to add
     */
    public void addRecord(TrainingRecord record) {
        this.records.add(record);
    }

    /**
     * Returns a String representing the dog's vocalization.
     * Each subclass provides its own unique implementation.
     *
     * @return a String describing how this dog speaks
     */
    public abstract String speak();

    /**
     * Returns the type of this dog.
     *
     * @return the {@link DogType} enum value for this dog
     */
    public abstract DogType getType();

    /**
     * Returns a formatted string representation of this dog.
     *
     * @return a string in the format "Type | ID: X | Name: Y | Age: Z | Breed: W"
     */
    @Override
    public String toString() {
        return getType().getLabel() + " | ID: " + dogID + " | Name: " + name
                + " | Age: " + age + " | Breed: " + breed;
    }
}
