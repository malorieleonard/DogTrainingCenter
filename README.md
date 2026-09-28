# Dog Training Management System

A Java desktop application for managing dogs enrolled in a training program and tracking their training progress over time.

The application was developed as a course project using **Java, JavaFX, Maven, and the Model-View-Controller (MVC) architecture**. It supports multiple types of dogs, training records, searching, dog behaviors, and persistent storage.

This project was completed from instructor-provided starter code. My work focused on implementing and completing portions of the model, persistence, testing, and controller functionality.

## Features

The application allows users to:

- Register therapy, police, and rescue dogs
- Store information about each dog
- Search for existing dogs
- Add training records to dogs
- Review a dog's training history
- Record training performance
- Trigger dog-specific behaviors and trained tricks
- Save dog information to files
- Load previously saved dog information
- Save and load training records

## Technologies

- Java
- JavaFX
- FXML
- Maven
- JUnit
- Git
- GitHub

## Architecture

The application follows the **Model-View-Controller (MVC)** design pattern.

### Model

The model contains the application's core data and business logic, including:

- `Dog`
- `PoliceDog`
- `RescueDog`
- `TherapyDog`
- `TrainingRecord`
- `DogManagement`
- `DogType`
- `Performance`
- `Trainable`
- `InvalidAgeException`

The different dog classes use inheritance to represent specialized types of dogs while sharing common functionality through the `Dog` superclass.

The `Trainable` interface defines behavior associated with dogs that can perform trained actions.

### View

The user interface is built with JavaFX and FXML.

Views include:

- Home
- Dog Registration
- Dog Search
- Add Training Record
- Training History
- Dog Behaviors
- Data Persistence

### Controller

Controller classes connect the JavaFX interface to the application's model and handle user interaction.

Controllers include:

- `HomeController`
- `RegisterDogController`
- `SearchDogController`
- `AddTrainingController`
- `HistoryController`
- `BehaviorController`
- `PersistenceController`

## Object-Oriented Programming

The project demonstrates several object-oriented programming concepts.

### Inheritance and Polymorphism

`PoliceDog`, `RescueDog`, and `TherapyDog` extend the base `Dog` class, allowing the application to represent different dog types while sharing common attributes and behaviors.

### Interfaces

The `Trainable` interface defines functionality for trained dog behaviors.

### Method Overriding

Specialized dog classes can provide behavior specific to their dog type through overridden methods.

### Exception Handling

The project includes a custom `InvalidAgeException` used to handle invalid dog age values.

## Data Persistence

The application can preserve dog and training information between sessions.

`DogManagement` provides functionality for:

- Saving dog records
- Loading dog records
- Saving training records
- Loading training records

Dog records and training records are stored separately.

## Testing

JUnit tests were used during development to test model functionality, including specialized dog classes and dog management behavior.

The project includes tests for model components such as:

- `PoliceDog`
- `RescueDog`
- `TherapyDog`
- `DogManagement`

## My Implementation

This project was developed from instructor-provided starter code as part of a multi-phase course assignment.

My implementation work included:

- Implementing `PoliceDog` and `RescueDog`
- Adding custom `InvalidAgeException` handling to the `Dog` model
- Completing dog and training-record persistence functionality in `DogManagement`
- Implementing file save/load functionality
- Creating JUnit tests for `PoliceDog` and `RescueDog`
- Completing JavaDoc documentation for assigned model components
- Implementing dog registration logic in `RegisterDogController`
- Implementing training-record creation logic in `AddTrainingController`
- Implementing save/load functionality in `PersistenceController`
- Integrating model functionality with the JavaFX MVC application

## Project Structure

```text
src/
├── main/
│   ├── java/
│   │   ├── module-info.java
│   │   └── org/dogtrainingapp/dog_training_app/
│   │       ├── DogTrainingApp.java
│   │       ├── controller/
│   │       └── model/
│   │
│   └── resources/
│       └── org/dogtrainingapp/dog_training_app/
│           └── view/
│
└── test/
    └── java/
        └── org/dogtrainingapp/dog_training_app/
            └── model/
```

## Course Project

This application was developed as a multi-phase class project for CSC 331.

The project focused on applying object-oriented programming principles, MVC architecture, JavaFX user-interface development, file persistence, exception handling, unit testing, and software documentation to a complete desktop application.
