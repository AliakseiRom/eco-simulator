package org.example.eco_simulator.run;

import org.example.eco_simulator.controller.EcoSystemController;
import org.example.eco_simulator.exception.CommonException;
import org.example.eco_simulator.exception.InvalidOrganismTypeException;
import org.example.eco_simulator.model.Animal;
import org.example.eco_simulator.model.Plant;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CommonException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of simulation(or create new):");
        String simName = scanner.nextLine();

        EcoSystemController ecosystem = new EcoSystemController(simName);

        boolean running = true;
        while (running) {
            System.out.println("""
                    Choose action:
                     [1] Add organism,
                     [2] Delete organism,
                     [3] Update organism,
                     [4] Update resource,
                     [5] Delete resource,
                     [6] View the contents of the ecosystem,
                     [7] Save simulation,
                     [8] Update eco conditions,
                     [9] View eco conditions,
                     [10] Exit""");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter name of organism:");
                    String name = scanner.nextLine();
                    System.out.println("Choose type (Plant/Animal):");
                    String type = scanner.nextLine();
                    if (!type.equalsIgnoreCase("Plant") && !type.equalsIgnoreCase("Animal")) {
                        throw new InvalidOrganismTypeException("Invalid organism type: " + type + ". Allowed types are 'Plant' or 'Animal'.");
                    }
                    System.out.println("Enter number of energy:");
                    Integer energy = scanner.nextInt();
                    scanner.nextLine();

                    if (type.equalsIgnoreCase("Plant")) {
                        ecosystem.addOrganism(new Plant(name, energy));
                    } else if (type.equalsIgnoreCase("Animal")) {
                        ecosystem.addOrganism(new Animal(name, energy));
                    }
                    break;
                case 2:
                    System.out.println("Enter name of organism for deleting:");
                    name = scanner.nextLine();
                    ecosystem.removeOrganism(name);
                    break;
                case 3:
                    System.out.println("Enter name of organism for updating:");
                    String oldName = scanner.nextLine();
                    System.out.println("Enter name of new organism:");
                    String updatedName = scanner.nextLine();
                    System.out.println("Choose type (Plant/Animal):");
                    String type1 = scanner.nextLine();
                    System.out.println("Enter number of energy:");
                    int energy1 = scanner.nextInt();
                    scanner.nextLine();
                    if (type1.equalsIgnoreCase("Plant")) {
                        ecosystem.updateOrganism(oldName, new Plant(updatedName, energy1));
                    } else {
                        ecosystem.updateOrganism(oldName, new Animal(updatedName, energy1));
                    }
                    break;
                case 4:
                    System.out.println("Enter resource(example: 'water'): ");
                    String resource = scanner.nextLine();
                    System.out.println("Enter value of resource: ");
                    int value = scanner.nextInt();
                    scanner.nextLine();
                    ecosystem.updateResource(resource, value);
                    break;
                case 5:
                    System.out.println("Enter name of resource to delete:");
                    String deletingResource = scanner.nextLine();
                    ecosystem.deleteResource(deletingResource);
                    break;
                case 6:
                    ecosystem.getAllOrganisms();
                    ecosystem.listResources();
                    ecosystem.displayEcoConditions();
                    break;
                case 7:
                    ecosystem.saveSimulation();
                    System.out.println("Simulation has been saved!.");
                    break;
                case 8:
                    System.out.println("Enter new temperature:");
                    double temperature = scanner.nextDouble();
                    System.out.println("Enter new humidity:");
                    double humidity = scanner.nextDouble();
                    ecosystem.updateEcoConditions(temperature, humidity);
                    break;
                case 9:
                    ecosystem.displayEcoConditions();
                    break;
                case 10:
                    running = false;
                    break;
                default:
                    System.out.println("Incorrect choice.");
                    System.out.println();
                    break;
            }
        }
        scanner.close();
    }
}