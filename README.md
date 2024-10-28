# Eco Simulator
EcoSimulator is a Java-based ecosystem simulation 
application that allows users to create, update, 
and manage various organisms and environmental 
conditions within a simulated ecosystem. 
The application provides a console-based interface 
to interact with the ecosystem, add or remove organisms, 
modify resources, and save/load simulation states.

## Author
Aleksei Romanovksij

## Getting started

### Requirements
To build and run this project, you will need the following:

* **JDK**: Install Java Development Kit 19
* **Maven**: Install Apache Maven 4.0.0
* **mvn clean install**: Perform mvn clean install
* **Running project**: Run the Project

## Usage
After starting the application, follow the on-screen 
instructions to interact with the ecosystem. You can 
add, update, and delete organisms, modify resources, 
and update environmental conditions.

## Classes and Functionality
### Main
The `Main` class contains the main loop and user menu. 
Users can choose actions like adding organisms, managing 
resources, and saving/loading simulations.

### EcoSystemController
The `EcoSystemController` class manages the entire ecosystem. 
It maintains lists of organisms, resources, and environmental 
conditions. It also includes methods to save and load the 
ecosystem to and from a file.

Key methods:
* `addOrganism`, `removeOrganism`, `updateOrganism`
* `updateResource`, `deleteResource`, `listResources`
* `updateEcoConditions`, `displayEcoConditions`
* `saveSimulation`, `loadSimulation`

### Models
* **Organism**: Abstract base class for all organisms.
* **Animal**: Extends Organism to represent animals 
with specific attributes.
* **Plant**: Extends Organism to represent 
plants with specific attributes.
* **EcoConditions**: Holds environmental data
(temperature and humidity) for the ecosystem.

### Exceptions
* **CommonException**: General exception for the application.
* **InvalidOrganismTypeException**: Thrown when an invalid 
organism type is specified.
* **OrganismNotFoundException**: Thrown when an organism 
is not found.
* **InvalidEcoConditionValueException**: Thrown for invalid 
eco condition values.
* **ResourceNotFoundException**: Thrown when a specified 
resource does not exist.

## Sample Usage
Here's an example of how to interact with the application:

1. **Start the Simulation**: Enter a name for a new 
or existing simulation.
2. **Add an Organism**: Choose option 1, then enter 
details for the organism (name, type, energy).
3. **Manage Resources**: Use options 4 or 5 to add or 
remove resources (e.g., water, food).
4. **Save Simulation**: Use option 7 to save the 
current state to a file.