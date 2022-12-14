package main.java.org.art.samples.core.algorithms.cci.linked_lists;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * "Animal Shelter" solution from "Cracking the Coding Interview".
 */
public class AnimalShelter {

    private final Queue<Animal> primaryList;

    private final Queue<Animal> secondaryList;

    private boolean secondaryWithCats;

    public AnimalShelter() {
        this.primaryList = new LinkedList<>();
        this.secondaryList = new LinkedList<>();
    }

    void enqueue(Animal animal) {
        primaryList.add(animal);
    }

    Animal dequeueAny() {
        if (!secondaryList.isEmpty()) {
            return secondaryList.poll();
        } else {
            return primaryList.poll();
        }
    }

    Cat dequeueCat() {
        if (secondaryWithCats && !secondaryList.isEmpty()) {
            return (Cat) secondaryList.poll();
        } else {
            Animal animal = primaryList.poll();
            do {
                if (animal instanceof Dog) {
                    secondaryWithCats = false;
                    secondaryList.add(animal);
                    animal = primaryList.poll();
                } else {
                    return (Cat) animal;
                }
            } while (!primaryList.isEmpty());
            if (animal instanceof Dog) {
                secondaryList.add(animal);
            }
        }
        return null;
    }

    Dog dequeueDog() {
        if (!secondaryWithCats && !secondaryList.isEmpty()) {
            return (Dog) secondaryList.poll();
        } else {
            Animal animal = primaryList.poll();
            do {
                if (animal instanceof Cat) {
                    secondaryWithCats = true;
                    secondaryList.add(animal);
                    animal = primaryList.poll();
                } else {
                    return (Dog) animal;
                }
            } while (!primaryList.isEmpty());
            if (animal instanceof Cat) {
                secondaryList.add(animal);
            }
        }
        return null;
    }

    @Test
    void test0() {
        AnimalShelter animalShelter = new AnimalShelter();
        animalShelter.enqueue(new Cat(1));
        animalShelter.enqueue(new Cat(2));
        animalShelter.enqueue(new Cat(3));
        animalShelter.enqueue(new Dog(4));
        animalShelter.enqueue(new Dog(5));
        assertSame(1, animalShelter.dequeueCat().number);
        assertSame(4, animalShelter.dequeueDog().number);
        assertSame(2, animalShelter.dequeueCat().number);
        assertSame(3, animalShelter.dequeueAny().number);
        assertNull(animalShelter.dequeueCat());
        assertSame(5, animalShelter.dequeueDog().number);
    }
}

abstract class Animal {

    int number;

    public Animal(int number) {
        this.number = number;
    }
}

class Cat extends Animal {

    public Cat(int number) {
        super(number);
    }
}

class Dog extends Animal {

    public Dog(int number) {
        super(number);
    }
}
