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

    public UUID getId() { return id; }
    public String getName() { return name; }
    public double getEnvironmentalImpactValue() { return environmentalImpactValue; }
    public String getRecyclingInstruction() { return recyclingInstruction; }

    public void setName(String name) { this.name = name; }
    public void setEnvironmentalImpactValue(double value) { this.environmentalImpactValue = value; }
    public void setRecyclingInstruction(String instruction) { this.recyclingInstruction = instruction; }
    
}
