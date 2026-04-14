package org.dogtrainingapp.dog_training_app.model;

/**
 * Represents a single training record for a dog.
 * Each record captures the skill practiced, date of training,and the dog's performance rating.
 *
 * <p>Note: This class does not store a dogID. The association between
 * a TrainingRecord and its Dog is maintained through the Dog's
 * {@code records} list. The dogID is only written to the file
 * during persistence by {@link DogManagement}.</p>
 */
public class TrainingRecord {
    private String skill;
    private String date;
    private Performance performance;

    /**
     * Constructs a new TrainingRecord with the specified details.
     *
     * @param skill       the name of the skill practiced (e.g., "Sit", "Track")
     * @param date        the date of the training session (e.g., "2026-04-10")
     * @param performance the performance rating for this training session
     */
    public TrainingRecord(String skill, String date, Performance performance) {
        this.skill = skill;
        this.date = date;
        this.performance = performance;
    }

    /**
     * Returns the name of the skill practiced.
     *
     * @return the skill name
     */
    public String getSkill() {return skill; }

    /**
     * Returns the date of the training session.
     *
     * @return the training date as a String
     */
    public String getDate() { return date; }

    /**
     * Returns the performance rating for this training session.
     *
     * @return the {@link Performance} enum value
     */
    public Performance getPerformance() { return performance; }

    /**
     * Returns a formatted string representation of this training record.
     *
     * @return a string in the format "Skill: X | Date: Y | Performance: Z"
     */
    @Override
    public String toString() {
        return "Skill: " + skill + " | Date: " + date + " | Performance: " + performance.getLabel();
    }
}
