package org.dogtrainingapp.dog_training_app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TherapyDogTest {

    private TherapyDog dog, dog2;

    @BeforeEach
    void setUp() {
        // Runs before EVERY test: a fresh dog object
        dog = new TherapyDog(1, "Izzy", 3, "Golden Retriever");
        dog2 = new TherapyDog(2, "Iron", -4, "Golden Retriever");
    }

    @Test
    @DisplayName("Test dog ID is set correctly")
    void testGetId() {
        assertEquals(1, dog.getDogID());
    }

    @Test
    @DisplayName("Test dog name is set correctly")
    void testGetName() {
        assertEquals("Izzy", dog.getName());
    }

    @Test
    @DisplayName("Test dog breed is set correctly")
    void testGetBreed() {
        assertEquals("Golden Retriever", dog.getBreed());
    }

    @Test
    @DisplayName("Test TherapyDog speak() returns correct message")
    void testSpeak() {
        String result = dog.speak();
        assertEquals("Izzy says: Woof! I'm here to comfort you!", result);
    }

    @Test
    @DisplayName("Test TherapyDog doTrick() returns correct message")
    void testDoTrick() {
        String result = dog.doTrick();
        assertEquals("Izzy gently places paw on your hand.", result);
    }

    @Test
    @DisplayName("Test TherapyDog is an instance of Dog")
    void testIsInstanceOfDog() {
        assertInstanceOf(Dog.class, dog);
    }

    @Test
    @DisplayName("Test TherapyDog implements Trainable")
    void testImplementsTrainable() {
        assertInstanceOf(Trainable.class, dog);
    }

    @Test
    void testGetAge(){
        assertEquals(0, dog2.getAge());
    }
}
