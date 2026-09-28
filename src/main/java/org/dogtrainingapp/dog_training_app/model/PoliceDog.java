package org.dogtrainingapp.dog_training_app.model;

/**
 * Represents a police dog in the training center.
 * Police dogs are trained for tasks such as patrol, tracking, and law enforcement support.
 */
public class PoliceDog extends Dog implements Trainable {

    /**
     * Constructs a PoliceDog with the specified information.
     *
     * @param id the dog ID
     * @param name the dog's name
     * @param age the dog's age
     * @param breed the dog's breed
     */
    public PoliceDog(int id, String name, int age, String breed) {
        super(id, name, age, breed);
    }

    /**
     * Returns this police dog's vocalization.
     *
     * @return a command-style bark message
     */
    @Override
    public String speak() {
        return getName() + " barks: Halt! Stay where you are!";
    }

    /**
     * Returns the type of this dog.
     *
     * @return DogType.POLICE
     */
    @Override
    public DogType getType() {
        return DogType.POLICE;
    }

    /**
     * Returns this police dog's trained trick.
     *
     * @return a string describing the trick
     */
    @Override
    public String doTrick() {
        return getName() + " swiftly searches the area for evidence.";
    }
}