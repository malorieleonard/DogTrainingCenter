package org.dogtrainingapp.dog_training_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.dogtrainingapp.dog_training_app.model.Dog;
import org.dogtrainingapp.dog_training_app.model.TrainingRecord;

public class HistoryController extends BaseController implements StatusAware {
    @FXML private TextField dogIdField;
    @FXML private ListView<String> historyListView;

    private HomeController homeController;

    @FXML
    private void loadHistory() {
        historyListView.getItems().clear();
        try {
            int dogId = Integer.parseInt(dogIdField.getText().trim());
            Dog dog = dogManagement.searchByID(dogId);
            if (dog == null) {
                historyListView.getItems().add("Dog not found.");
                updateStatus("Dog not found.");
                return;
            }
            historyListView.getItems().add("Dog: " + dog);
            if (dog.getRecords().isEmpty()) {
                historyListView.getItems().add("No training history available.");
            } else {
                for (TrainingRecord record : dog.getRecords()) {
                    historyListView.getItems().add(record.toString());
                }
            }
            updateStatus("Training history displayed for " + dog.getName() + ".");
        } catch (NumberFormatException e) {
            updateStatus("Enter a valid numeric dog ID.");
        }
    }

    private void updateStatus(String message) {
        if (homeController != null) {
            homeController.setStatus(message);
        }
    }

    @Override
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
