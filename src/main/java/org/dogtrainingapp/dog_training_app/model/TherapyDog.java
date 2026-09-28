package org.dogtrainingapp.dog_training_app.model;

/**
 * Represents a therapy dog in the training center.
 * Therapy dogs are trained to provide comfort and emotional support.
 */
public class TherapyDog extends Dog implements Trainable {

    /**
     * Constructs a TherapyDog with the specified information.
     *
     * @param id the dog ID
     * @param name the dog's name
     * @param age the dog's age
     * @param breed the dog's breed
     */
    public TherapyDog(int id, String name, int age, String breed) {
        super(id, name, age, breed);
    }

    /**
     * Returns this therapy dog's vocalization.
     *
     * @return a comforting bark message
     */
    @Override
    public String speak() {
        return getName() + " says: Woof! I'm here to comfort you!";
    }

    /**
     * Returns the type of this dog.
     *
     * @return DogType.THERAPY
     */
    @Override
    public DogType getType() {
        return DogType.THERAPY;
    }

    /**
     * Returns this therapy dog's trained trick.
     *
     * @return a string describing the trick
     */
    @Override
    public String doTrick() {
        return getName() + " gently places paw on your hand.";
    }
}