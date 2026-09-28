package org.dogtrainingapp.dog_training_app.model;

/**
 * Represents a rescue dog in the training center.
 * Rescue dogs are trained to locate missing people and assist in emergency response tasks.
 */
public class RescueDog extends Dog implements Trainable {

    /**
     * Constructs a RescueDog with the specified information.
     *
     * @param id the dog ID
     * @param name the dog's name
     * @param age the dog's age
     * @param breed the dog's breed
     */
    public RescueDog(int id, String name, int age, String breed) {
        super(id, name, age, breed);
    }

    /**
     * Returns this rescue dog's vocalization.
     *
     * @return a rescue-themed bark message
     */
    @Override
    public String speak() {
        return getName() + " barks: I found someone! Over here!";
    }

    /**
     * Returns the type of this dog.
     *
     * @return DogType.RESCUE
     */
    @Override
    public DogType getType() {
        return DogType.RESCUE;
    }

    /**
     * Returns this rescue dog's trained trick.
     *
     * @return a string describing the trick
     */
    @Override
    public String doTrick() {
        return getName() + " sniffs the ground and leads the team to a safe location.";
    }
}