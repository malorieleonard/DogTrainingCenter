package org.dogtrainingapp.dog_training_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dogtrainingapp.dog_training_app.controller.HomeController;
import org.dogtrainingapp.dog_training_app.model.DogManagement;

public class DogTrainingApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/dogtrainingapp/dog_training_app/view/HomeView.fxml"));
        Scene scene = new Scene(loader.load(), 1050, 680);

        DogManagement dogManagement = new DogManagement();
        HomeController homeController = loader.getController();
        homeController.setDogManagement(dogManagement);

        stage.setTitle("Dog Training App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
