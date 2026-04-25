package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {
    private final UUID id;
    private String name;
    private int lifespanYears;
    private String category;
    public List<Material> materials = new ArrayList<>();

    public Product(String name, String category, int lifespanYears) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.category = category;
        this.lifespanYears = lifespanYears;
        this.materials = new ArrayList<>();
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }

    public double calculateImpact(ImpactStrategy strategy) {
        return strategy.calculate(this);
    }

    // recyclingGuidance()

    // Getters TODO
    // return id, name, category, lifespan, materials

    // Setters TODO
    // setName, setCategory, setLifespan

}