package org.dogtrainingapp.dog_training_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.dogtrainingapp.dog_training_app.model.Dog;
import org.dogtrainingapp.dog_training_app.model.Trainable;

public class BehaviorController extends BaseController implements StatusAware {
    @FXML private TextField dogIdField;
    @FXML private TextArea outputArea;
    @FXML private Label messageLabel;

    private HomeController homeController;

    @FXML
    private void makeDogSpeak() {
        Dog dog = findDog();
        if (dog != null) {
            outputArea.setText(dog.speak());
            showMessage("Speak action completed for " + dog.getName() + ".");
        }
    }

    @FXML
    private void performTrick() {
        Dog dog = findDog();
        if (dog == null) {
            return;
        }
        if (dog instanceof Trainable trainable) {
            outputArea.setText(trainable.doTrick());
            showMessage("Trick action completed for " + dog.getName() + ".");
        } else {
            outputArea.setText("");
            showMessage("This dog does not support trained tricks.");
        }
    }

    private Dog findDog() {
        try {
            int id = Integer.parseInt(dogIdField.getText().trim());
            Dog dog = dogManagement.searchByID(id);
            if (dog == null) {
                showMessage("No dog found with ID " + id + ".");
            }
            return dog;
        } catch (NumberFormatException e) {
            showMessage("Enter a valid numeric dog ID.");
            return null;
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
