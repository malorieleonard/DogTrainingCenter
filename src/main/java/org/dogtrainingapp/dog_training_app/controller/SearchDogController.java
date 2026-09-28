package org.dogtrainingapp.dog_training_app.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.dogtrainingapp.dog_training_app.model.Dog;
import org.dogtrainingapp.dog_training_app.model.DogType;

import java.util.List;

public class SearchDogController extends BaseController implements StatusAware {
    @FXML private TextField idSearchField;
    @FXML private TextField nameSearchField;
    @FXML private ComboBox<DogType> typeComboBox;
    @FXML private ListView<String> resultListView;

    private HomeController homeController;

    @FXML
    public void initialize() {
        typeComboBox.setItems(FXCollections.observableArrayList(DogType.values()));
    }

    @FXML
    private void searchById() {
        resultListView.getItems().clear();
        try {
            int id = Integer.parseInt(idSearchField.getText().trim());
            Dog dog = dogManagement.searchByID(id);
            if (dog == null) {
                resultListView.getItems().add("No dog found with ID " + id + ".");
                updateStatus("No dog found with ID " + id + ".");
                return;
            }
            resultListView.getItems().add(dog.toString());
            updateStatus("Found dog with ID " + id + ".");
        } catch (NumberFormatException e) {
            updateStatus("Enter a valid numeric ID.");
        }
    }

    @FXML
    private void searchByName() {
        resultListView.getItems().clear();
        String name = nameSearchField.getText().trim();
        if (name.isBlank()) {
            updateStatus("Enter a name to search.");
            return;
        }
        List<Dog> dogs = dogManagement.searchByName(name);
        if (dogs.isEmpty()) {
            resultListView.getItems().add("No dogs found with name: " + name);
        } else {
            dogs.forEach(d -> resultListView.getItems().add(d.toString()));
        }
        updateStatus("Search by name completed.");
    }

    @FXML
    private void searchByType() {
        resultListView.getItems().clear();
        DogType type = typeComboBox.getValue();
        if (type == null) {
            updateStatus("Select a dog type first.");
            return;
        }
        List<Dog> dogs = dogManagement.searchByType(type);
        if (dogs.isEmpty()) {
            resultListView.getItems().add("No dogs found for type: " + type.getLabel());
        } else {
            dogs.forEach(d -> resultListView.getItems().add(d.toString()));
        }
        updateStatus("Search by type completed.");
    }

    @FXML
    private void showAllDogs() {
        resultListView.getItems().clear();
        if (dogManagement.getDogs().isEmpty()) {
            resultListView.getItems().add("No dogs are currently registered.");
        } else {
            dogManagement.getDogs().forEach(d -> resultListView.getItems().add(d.toString()));
        }
        updateStatus("Displayed all registered dogs.");
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
