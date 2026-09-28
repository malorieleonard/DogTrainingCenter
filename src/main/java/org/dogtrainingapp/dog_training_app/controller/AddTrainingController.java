package org.dogtrainingapp.dog_training_app.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.dogtrainingapp.dog_training_app.model.*;

public class AddTrainingController extends BaseController implements StatusAware {
    @FXML private TextField dogIdField;
    @FXML private TextField skillField;
    @FXML private TextField dateField;
    @FXML private ComboBox<Performance> performanceComboBox;
    @FXML private Label messageLabel;

    private HomeController homeController;

    @FXML
    public void initialize() {
        performanceComboBox.setItems(FXCollections.observableArrayList(Performance.values()));
        performanceComboBox.getSelectionModel().select(Performance.GOOD);
    }

    @FXML
    private void addRecord() {
        try {
            int dogId = Integer.parseInt(dogIdField.getText().trim());
            String skill = skillField.getText().trim();
            String date = dateField.getText().trim();
            Performance performance = performanceComboBox.getValue();

  	    // check if skill, date and performance are filled in 
            if (skill.isBlank() || date.isBlank() || performance == null) {
                showMessage("Please complete all training fields.");
                return;
            }

            if (!isValidDate(date)) {
                showMessage("Date must be in YYYY-MM-DD format.");
                return;
            }

            Dog dog = dogManagement.searchByID(dogId);

            if (dog == null) {
                showMessage("No dog found with ID " + dogId + ".");
                return;
            }

            TrainingRecord record = new TrainingRecord(skill, date, performance);
            dog.addRecord(record);

            showMessage("Training record added for " + dog.getName() + ".");
            clearFields();

        } catch (NumberFormatException e) {
            showMessage("Dog ID must be a whole number.");
        }
    }

    //clear all the fields
    @FXML
    private void clearFields() {
        dogIdField.clear();
        skillField.clear();
        dateField.clear();
        performanceComboBox.getSelectionModel().select(Performance.GOOD);
        clearMessages();
    }

    // check if a input date is in the valid format 
    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // clear all the messages
    private void clearMessages() {
        messageLabel.setText("");
        if (homeController != null) {
            homeController.setStatus("");
        }
    }

    // display messages
    private void showMessage(String message) {
        messageLabel.setText(message);
        if (homeController != null) {
            homeController.setStatus(message);
        }
    }

    @Override
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
