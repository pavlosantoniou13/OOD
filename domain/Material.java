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

    // Getters TODO
    // return id, name, environmentalImpactValue

    // Setters TODO
    // setName(), setEnvironmentalImpactValue(), setRecyclingInstruction()
}
