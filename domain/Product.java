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

   public RecyclingGuidance getRecyclingGuidance() {
        List<MaterialQuantity> materials = getMaterialQuantities();

        if (materials.isEmpty()) {
            return new RecyclingGuidance(false, "No materials registered for this product.");
        }

        boolean isMixed = materials.size() > 1;

        if (!isMixed) {
            Material material = materials.get(0).material();
            return new RecyclingGuidance(false, 
                "Single-material product. " + material.getRecyclingInstruction() + 
                " (Recyclability: " + String.format("%.0f%%", material.getRecyclabilityScore() * 100) + ")");
        }

        // Mixed material product
        StringBuilder message = new StringBuilder();
        message.append("Mixed-material product. Disassemble and recycle components separately:");

        for (MaterialQuantity mq : materials) {
            Material m = mq.material();
            message.append("  - ").append(m.getName())
                   .append(" (").append(String.format("%.2f", mq.quantity())).append(" kg): ")
                   .append(m.getRecyclingInstruction())
                   .append(" (Recyclability: ").append(String.format("%.0f%%", m.getRecyclabilityScore() * 100)).append(")\n");
        }

        return new RecyclingGuidance(true, message.toString().trim());
    }

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