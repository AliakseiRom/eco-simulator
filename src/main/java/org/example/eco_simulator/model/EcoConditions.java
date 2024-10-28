package org.example.eco_simulator.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EcoConditions {
    private double temperature;
    private double humidity;

    public EcoConditions(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Temperature: " + temperature + ", Humidity: " + humidity;
    }
}