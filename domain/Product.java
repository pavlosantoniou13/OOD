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

    // recyclingGuidance() TODO

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getLifespanYears() {
        return lifespanYears;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLifespanYears(int lifespanYears) {
        this.lifespanYears = lifespanYears;
    }
}