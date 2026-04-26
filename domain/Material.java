package domain;

import java.util.UUID;

public class Material {
    private final UUID id;
    private String name;
    private double environmentalImpactValue;
    private String recyclingInstruction;

    public Material(String name, double environmentalImpactValue, String recyclingInstruction) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.environmentalImpactValue = environmentalImpactValue;
        this.recyclingInstruction = recyclingInstruction;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getEnvironmentalImpactValue() {
        return environmentalImpactValue;
    }

    public String getRecyclingInstruction() {
        return recyclingInstruction;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEnvironmentalImpactValue(double environmentalImpactValue) {
        this.environmentalImpactValue = environmentalImpactValue;
    }

    public void setRecyclingInstruction(String recyclingInstruction) {
        this.recyclingInstruction = recyclingInstruction;
    }
}