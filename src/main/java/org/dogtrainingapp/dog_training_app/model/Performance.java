package org.dogtrainingapp.dog_training_app.model;

/**
 * Represents the performance rating of a dog during a training session.
 * Each constant has a label for display purposes.
 *
 * <p>Usage:</p>
 * <ul>
 *   <li>{@code name()} — returns the constant name (e.g., "EXCELLENT") for file I/O</li>
 *   <li>{@code valueOf(String)} — converts a constant name back to the enum</li>
 *   <li>{@code getLabel()} — returns the display label (e.g., "Excellent") for UI</li>
 * </ul>
 */
public enum Performance {
    EXCELLENT("Excellent"),
    GOOD("Good"),
    SATISFACTORY("Satisfactory"),
    NEEDS_IMPROV("Needs Improvement");

    private final String label;

    /**
     * Constructs a Performance rating with the specified display label.
     *
     * @param label the label for this performance level
     */
    Performance(String label) {
        this.label = label;
    }

    /**
     * Returns the label for this performance rating.
     *
     * @return the display label (e.g., "Excellent")
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
