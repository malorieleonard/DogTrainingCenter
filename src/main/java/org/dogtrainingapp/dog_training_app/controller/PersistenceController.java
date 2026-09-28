package org.dogtrainingapp.dog_training_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.dogtrainingapp.dog_training_app.model.InvalidAgeException;

public class PersistenceController extends BaseController implements StatusAware {
    @FXML private Label messageLabel;

    private HomeController homeController;

    @FXML
    private void saveData() {
        dogManagement.saveDogs();
        dogManagement.saveRecords();
        showMessage("Dog and training data saved successfully.");
    }

    @FXML
    private void loadData() {
        try {
            dogManagement.loadDogs();
            dogManagement.loadRecords();
            showMessage("Dog and training data loaded successfully.");
        } catch (Exception e) {
            showMessage("Error loading data.");
        }
    }

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
