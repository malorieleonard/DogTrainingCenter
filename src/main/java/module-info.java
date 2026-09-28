module org.dogtrainingapp.dog_training_app {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.dogtrainingapp.dog_training_app;
    exports org.dogtrainingapp.dog_training_app.model;
    exports org.dogtrainingapp.dog_training_app.controller;

    opens org.dogtrainingapp.dog_training_app to javafx.fxml;
    opens org.dogtrainingapp.dog_training_app.controller to javafx.fxml;
}
