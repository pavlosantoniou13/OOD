package OOD.application;

import OOD.domain.Material;
import OOD.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SimpleSumStrategyTest {

    private SimpleSumStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new SimpleSumStrategy();
    }

    @Test
    @DisplayName("Should return zero when product has no materials")
    void shouldReturnZeroWhenProductHasNoMaterials() {
        
        Product product = new Product("Empty", "Unknown", 0);

        
        double result = strategy.calculate(product);

        
        assertEquals(0.0, result, 0.001);
    }

    @Test
    @DisplayName("Should sum impact values of all materials")
    void shouldSumImpactValuesOfAllMaterials() {
        
        Material plastic = new Material("Plastic", 5.0, "High recyclability");
        Material glass = new Material("Glass", 2.5, "High recyclability");
        Product bottle = new Product("Bottle", "Container", 24);
        bottle.addMaterial(plastic, 1.0);
        bottle.addMaterial(glass, 1.0);

        
        double result = strategy.calculate(bottle);

        // Assert: (1.0 * 5.0) + (1.0 * 2.5) = 7.5
        assertEquals(7.5, result, 0.001);
    }

    @Test
    @DisplayName("Should calculate with different quantities")
    void shouldCalculateWithDifferentQuantities() {
        
        Material plastic = new Material("Plastic", 5.0, "High recyclability");
        Material glass = new Material("Glass", 2.5, "High recyclability");
        Product bottle = new Product("Bottle", "Container", 24);
        bottle.addMaterial(plastic, 2.0);  
        bottle.addMaterial(glass, 0.5);   

        
        double result = strategy.calculate(bottle);

        // Assert: (2.0 * 5.0) + (0.5 * 2.5) = 10.0 + 1.25 = 11.25
        assertEquals(11.25, result, 0.001);
    }

    @Test
    @DisplayName("Should calculate single material product")
    void shouldCalculateSingleMaterialProduct() {
        
        Material metal = new Material("Metal", 3.0, "Medium recyclability");
        Product can = new Product("Can", "Container", 12);
        can.addMaterial(metal, 0.3);

        
        double result = strategy.calculate(can);

        // Assert: 0.3 * 3.0 = 0.9
        assertEquals(0.9, result, 0.001);
    }

    @Test
    @DisplayName("Should handle zero quantity material")
    void shouldHandleZeroQuantityMaterial() {
        
        Material plastic = new Material("Plastic", 5.0, "High recyclability");
        Product product = new Product("Minimal", "Test", 1);
        product.addMaterial(plastic, 0.0);

        
        double result = strategy.calculate(product);

        // Assert: 0.0 * 5.0 = 0.0
        assertEquals(0.0, result, 0.001);
    }

    @Test
    @DisplayName("Should handle large impact values")
    void shouldHandleLargeImpactValues() {
       
        Material highImpact = new Material("Toxic", 1000.0, "Low recyclability");
        Product hazardous = new Product("Hazardous", "Chemical", 1);
        hazardous.addMaterial(highImpact, 2.5);

        
        double result = strategy.calculate(hazardous);

        // Assert: 2.5 * 1000.0 = 2500.0
        assertEquals(2500.0, result, 0.001);
    }
}
