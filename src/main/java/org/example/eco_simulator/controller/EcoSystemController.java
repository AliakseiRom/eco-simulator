package org.example.eco_simulator.controller;

import org.example.eco_simulator.exception.*;
import org.example.eco_simulator.model.EcoConditions;
import org.example.eco_simulator.model.Organism;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcoSystemController {
    private List<Organism> organisms;
    private Map<String, Integer> resources;
    private EcoConditions ecoConditions;
    private String simulationName;

    public EcoSystemController(String simulationName) throws CommonException {
        this.simulationName = simulationName;
        organisms = new ArrayList<>();
        resources = new HashMap<>();
        ecoConditions = new EcoConditions(20.0, 50.0);
        loadSimulation();
    }

    public void addOrganism(Organism organism) throws CommonException {
        organisms.add(organism);
    }

    public void removeOrganism(String name) throws CommonException {
        boolean removed = organisms.removeIf(o -> o.getName().equalsIgnoreCase(name));
        if (!removed) {
            throw new OrganismNotFoundException("Organism with name " + name + " not found");
        }
    }

    public void updateOrganism(String name, Organism updatedOrganism) throws CommonException {
        String type = updatedOrganism.getType();
        if (!type.equalsIgnoreCase("Plant") && !type.equalsIgnoreCase("Animal")) {
            throw new InvalidOrganismTypeException("Invalid organism type: " + type + ". Allowed types are 'Plant' or 'Animal'.");
        }

        boolean updated = false;
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i).getName().equalsIgnoreCase(name)) {
                organisms.set(i, updatedOrganism);
                updated = true;
                break;
            }
        }
        if (!updated) {
            throw new OrganismNotFoundException(name);
        }
    }

    public void getAllOrganisms() {
        for (Organism organism : organisms) {
            System.out.println(organism);
        }
    }

    public void updateEcoConditions(double temperature, double humidity) throws CommonException {
        if (temperature < -50 || temperature > 50) {
            throw new InvalidEcoConditionValueException("Temperature must be between -50 and 50.");
        }
        if (humidity < 0 || humidity > 100) {
            throw new InvalidEcoConditionValueException("Humidity must be between 0 and 100.");
        }
        ecoConditions.setTemperature(temperature);
        ecoConditions.setHumidity(humidity);
    }

    public void displayEcoConditions() {
        System.out.println("Eco Conditions: " + ecoConditions);
    }

    public void saveSimulation() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(simulationName + ".txt"))) {
            for (Organism organism : organisms) {
                writer.write(organism.toString());
                writer.newLine();
            }
            writer.write(ecoConditions.toString());
            writer.newLine();

            StringBuilder resourcesContent = new StringBuilder("Resources: {");
            for (Map.Entry<String, Integer> entry : resources.entrySet()) {
                resourcesContent.append(entry.getKey()).append(" = ").append(entry.getValue()).append(", ");
            }

            if (!resources.isEmpty()) {
                resourcesContent.setLength(resourcesContent.length() - 2);
            }

            resourcesContent.append("}");

            writer.write(resourcesContent.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSimulation() throws CommonException {
        File file = new File(simulationName + ".txt");
        if (!file.exists()) {
            System.out.println("New Simulation.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Plant") || line.startsWith("Animal")) {
                    Organism organism = Organism.parseOrganism(line);
                    addOrganism(organism);
                } else if (line.startsWith("Resources")) {
                    String[] resData = line.split(":")[1].replace("{", "").replace("}", "").split(",");
                    for (String res : resData) {
                        String[] keyValue = res.split("=");
                        resources.put(keyValue[0].trim(), Integer.parseInt(keyValue[1].trim()));
                    }
                } else if (line.startsWith("EcoConditions")) {
                    String[] condData = line.split(",");
                    double temp = Double.parseDouble(condData[0].split(":")[1].trim());
                    double hum = Double.parseDouble(condData[1].split(":")[1].trim());
                    ecoConditions = new EcoConditions(temp, hum);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateResource(String resource, int value) {
        resources.put(resource, value);
    }

    public void deleteResource(String resource) throws CommonException {
        if (!resources.containsKey(resource)) {
            throw new ResourceNotFoundException(resource);
        }
        resources.remove(resource);
    }

    public void listResources() {
        System.out.println("Resources " + resources);
    }
}
