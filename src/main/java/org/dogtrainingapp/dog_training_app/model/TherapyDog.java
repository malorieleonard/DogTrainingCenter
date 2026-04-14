package org.dogtrainingapp.dog_training_app.model;

public class TherapyDog extends Dog implements Trainable {
    public TherapyDog(int id, String name, int age, String breed) {
        super(id, name, age, breed);
    }

    @Override
    public String speak() {
        return getName() + " says: Woof! I'm here to comfort you!";
    }

    @Override
    public DogType getType() {
        return DogType.THERAPY;
    }

    @Override
    public String doTrick() {
        return getName() + " gently places paw on your hand.";
    }
}
