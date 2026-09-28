package org.dogtrainingapp.dog_training_app.model;

/**
 * Custom exception used when an invalid dog age is provided.
 */
public class InvalidAgeException extends Exception {

    /**
     * Constructs an InvalidAgeException with the specified message.
     *
     * @param message the detail message
     */
    public InvalidAgeException(String message) {
        super(message);
    }
}