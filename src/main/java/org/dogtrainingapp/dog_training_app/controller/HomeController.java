package org.dogtrainingapp.dog_training_app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.dogtrainingapp.dog_training_app.model.DogManagement;

import java.io.IOException;

public class HomeController {
    @FXML
    private BorderPane rootPane;

    @FXML
    private Label statusLabel;

    private DogManagement dogManagement;

    @FXML
    public void initialize() {
        setStatus("Ready");
    }

    public void setDogManagement(DogManagement dogManagement) {
        this.dogManagement = dogManagement;
        loadView("RegisterDogView.fxml");
    }

    @FXML
    private void showRegisterDog() {
        loadView("RegisterDogView.fxml");
    }

    @FXML
    private void showAddTraining() {
        loadView("AddTrainingView.fxml");
    }

    @FXML
    private void showSearchDogs() {
        loadView("SearchDogView.fxml");
    }

    @FXML
    private void showBehavior() {
        loadView("BehaviorView.fxml");
    }

    @FXML
    private void showHistory() {
        loadView("HistoryView.fxml");
    }

    @FXML
    private void showPersistence() {
        loadView("PersistenceView.fxml");
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/dogtrainingapp/dog_training_app/view/" + fxmlFile));
            Node content = loader.load();
            Object controller = loader.getController();

            if (controller instanceof BaseController baseController) {
                baseController.setDogManagement(dogManagement);
            }
            if (controller instanceof StatusAware statusAware) {
                statusAware.setHomeController(this);
            }

            rootPane.setCenter(content);
            setStatus("Loaded: " + fxmlFile.replace("View.fxml", ""));
        } catch (IOException e) {
            setStatus("Unable to load view: " + fxmlFile);
            e.printStackTrace();
        }
    }
}
