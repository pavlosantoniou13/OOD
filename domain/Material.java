package domain;

import java.util.UUID;

public class Material {
    private final UUID id;
    private String name;
    private double environmentalImpactValue;
    private String recyclingInstruction;
    private double recyclabilityScore;

    public Material(String name, double environmentalImpactValue, String recyclingInstruction) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.environmentalImpactValue = environmentalImpactValue;
        this.recyclingInstruction = recyclingInstruction;
        this.recyclabilityScore = parseRecyclability(recyclingInstruction);
    }

    private double parseRecyclability(String instruction) {
        String lower = instruction.toLowerCase();
        if (lower.contains("high")) return 0.9;
        if (lower.contains("medium")) return 0.6;
        if (lower.contains("low")) return 0.2;
        return 0.5;
    }

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

    public double getRecyclabilityScore() { 
        return recyclabilityScore; 
    }

    public void setName(String name) { 
        this.name = name; 
    }
    
    public void setEnvironmentalImpactValue(double value) { 
        this.environmentalImpactValue = value; 
    }

    public void setRecyclingInstruction(String instruction) { 
        this.recyclingInstruction = instruction; 
        this.recyclabilityScore = parseRecyclability(instruction);
    }
}