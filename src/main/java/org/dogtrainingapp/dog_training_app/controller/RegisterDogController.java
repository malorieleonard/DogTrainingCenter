package org.dogtrainingapp.dog_training_app.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.dogtrainingapp.dog_training_app.model.*;

public class RegisterDogController extends BaseController implements StatusAware {
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField breedField;
    @FXML private ComboBox<DogType> typeComboBox;
    @FXML private Label messageLabel;

    private HomeController homeController;

    @FXML
    public void initialize() {
        typeComboBox.setItems(FXCollections.observableArrayList(DogType.values()));
        typeComboBox.getSelectionModel().select(DogType.THERAPY);
    }

    @FXML
    private void registerDog() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();

            int age = Integer.parseInt(ageField.getText().trim());
            String breed = breedField.getText().trim();
            DogType type = typeComboBox.getValue();

            if (name.isBlank() || breed.isBlank() || type == null) {
                showMessage("Please complete all dog registration fields.");
                return;
            }

            if (dogManagement.searchByID(id) != null) {
                showMessage("A dog with ID " + id + " already exists.");
                return;
            }

            Dog dog = createDog(type, id, name, age, breed);
            dogManagement.addDog(dog);

            showMessage("Dog registered successfully: " + name);
            clearFields();

        } catch (NumberFormatException e) {
            showMessage("ID and age must be whole numbers.");
        } catch (InvalidAgeException e) {
            showMessage("Invalid age: " + e.getMessage());
        }
    }

    // create dog instance with the given type
    private Dog createDog(DogType type, int id, String name, int age, String breed) throws InvalidAgeException {
        return switch (type) {
            case THERAPY -> new TherapyDog(id, name, age, breed);
            case POLICE -> new PoliceDog(id, name, age, breed);
            case RESCUE -> new RescueDog(id, name, age, breed);
        };
    }

    // clear all the fields
    @FXML
    private void clearFields() {
        idField.clear();
        nameField.clear();
        ageField.clear();
        breedField.clear();
        typeComboBox.getSelectionModel().select(DogType.THERAPY);
        clearMessages();
    }

    // clear displayed messages 
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
