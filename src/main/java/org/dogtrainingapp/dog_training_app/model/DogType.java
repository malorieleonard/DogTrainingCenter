package org.dogtrainingapp.dog_training_app.model;

/**
 * Represents the type of a dog in the training center.
 * Each constant has a label for display purposes.
 *
 * <p>Usage:</p>
 * <ul>
 *   <li>{@code name()} — returns the constant name (e.g., "THERAPY") for file I/O</li>
 *   <li>{@code valueOf(String)} — converts a constant name back to the enum</li>
 *   <li>{@code getLabel()} — returns the display label (e.g., "Therapy dog") for UI</li>
 * </ul>
 */

public enum DogType {
    THERAPY("Therapy dog"),
    POLICE("Police dog"),
    RESCUE("Rescue dog");

    private final String label;

    /**
     * Constructs a DogType with the specified display label.
     *
     * @param label the label for this dog type
     */
    DogType(String label) {
        this.label = label;
    }

    /**
     * Returns the label for this dog type.
     * @return the display label (e.g., "Therapy dog")
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the label for display in UI components.
     *
     * @return the display label
     */
    @Override
    public String toString() {
        return label;
    }
}