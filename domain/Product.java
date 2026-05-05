package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {
    private final UUID id;
    private String name;
    private int lifespanYears;
    private String category;
    public List<MaterialQuantity> materialQuantities = new ArrayList<>();

    public Product(String name, String category, int lifespanYears) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.category = category;
        this.lifespanYears = lifespanYears;
    }

    public void addMaterial(Material material, double quantity) {
        materialQuantities.add(new MaterialQuantity(material, quantity));
    }

    public double calculateImpact(ImpactStrategy strategy) {
        return strategy.calculate(this);
    }

    // recyclingGuidance()

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getLifespanYears() { return lifespanYears; }
    public List<MaterialQuantity> getMaterialQuantities() { 
        return new ArrayList<>(materialQuantities); 
    }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setLifespanYears(int lifespanYears) { this.lifespanYears = lifespanYears; }

}