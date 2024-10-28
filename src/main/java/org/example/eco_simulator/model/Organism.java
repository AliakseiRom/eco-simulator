package org.example.eco_simulator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Organism {
    @Getter
    protected String name;
    @Getter
    protected String type;
    protected Integer energy;

    public Organism(String name, String type, int energy) {
        this.name = name;
        this.type = type;
        this.energy = energy;
    }

    public String toString() {
        return null;
    }

    public static Organism parseOrganism(String data) {
        String[] parts = data.split(",");
        String name = parts[0].split("=")[1];
        String type = parts[0].split(" \\{")[0];
        String bufferForEnergy = parts[1].split("=")[1];

        int energy = Integer.parseInt(bufferForEnergy.split("}")[0]);

        if (type.equalsIgnoreCase("Plant")) {
            return new Plant(name, energy);
        } else {
            return new Animal(name, energy);
        }
    }
}