package org.example.eco_simulator.model;

public class Plant extends Organism {

    public Plant(String name, int energy) {
        super(name, "Plant", energy);
    }

    @Override
    public String toString() {
        return "Plant{name=" + name + ", energy=" + energy + "}";
    }
}