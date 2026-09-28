package org.dogtrainingapp.dog_training_app.model;

/**
 * Represents a behavior interface for dogs that can perform a trained trick or task.
 */
public interface Trainable {

    /**
     * Returns a description of the trained trick or behavior performed by the dog.
     *
     * @return a string describing the trick
     */
    String doTrick();
}