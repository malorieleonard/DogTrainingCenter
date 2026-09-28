package org.dogtrainingapp.dog_training_app.controller;

import org.dogtrainingapp.dog_training_app.model.DogManagement;

public abstract class BaseController {
    protected DogManagement dogManagement;

    public void setDogManagement(DogManagement dogManagement) {
        this.dogManagement = dogManagement;
    }
}
