package OOD.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {
    private final UUID id;
    private String name;
    private int lifespanYears;
    private String category;
    private List<MaterialQuantity> materialQuantities = new ArrayList<>();

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

    // REFACTORED: Extracted getRecyclingGuidance into smaller methods
    public RecyclingGuidance getRecyclingGuidance() {
        List<MaterialQuantity> materials = getMaterialQuantities();

        if (materials.isEmpty()) {
            return createEmptyGuidance();
        }

        if (materials.size() == 1) {
            return createSingleMaterialGuidance(materials.get(0));
        }

        return createMixedMaterialGuidance(materials);
    }

    // Empty product 
    private RecyclingGuidance createEmptyGuidance() {
        return new RecyclingGuidance(false, "No materials registered for this product.");
    }

    // Single material 
    private RecyclingGuidance createSingleMaterialGuidance(MaterialQuantity mq) {
        Material material = mq.material();
        String message = String.format(
            "Single-material product. %s (Recyclability: %.0f%%)",
            material.getRecyclingInstruction(),
            material.getRecyclabilityScore() * 100
        );
        return new RecyclingGuidance(false, message);
    }

    // EXTRACTED METHOD: Mixed material 
    private RecyclingGuidance createMixedMaterialGuidance(List<MaterialQuantity> materials) {
        StringBuilder message = new StringBuilder();
        message.append("Mixed-material product. Disassemble and recycle components separately:");

        for (MaterialQuantity mq : materials) {
            Material m = mq.material();
            message.append(String.format(
                "\n  - %s (%.2f kg): %s (Recyclability: %.0f%%)",
                m.getName(),
                mq.quantity(),
                m.getRecyclingInstruction(),
                m.getRecyclabilityScore() * 100
            ));
        }

        return new RecyclingGuidance(true, message.toString().trim());
    }

    // Getters
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