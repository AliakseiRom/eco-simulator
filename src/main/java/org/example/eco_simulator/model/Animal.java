package org.example.eco_simulator.model;

public class Animal extends Organism {

    public Animal(String name, int energy) {
        super(name, "Animal", energy);
    }

    @Override
    public String toString() {
        return "Animal {name=" + name + ", energy=" + energy + "}";
    }
}