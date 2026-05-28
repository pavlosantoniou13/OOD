package OOD.application;

import OOD.domain.Material;
import OOD.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedStrategyTest {

    private WeightedStrategy strategy;
    private static final double DELTA = 0.15;
    private static final double TOLERANCE = 0.001;

    @BeforeEach
    void setUp() {
        strategy = new WeightedStrategy();
    }

    @Test
    @DisplayName("Should return zero when product has no materials")
    void shouldReturnZeroWhenProductHasNoMaterials() {
        // Arrange
        Product product = new Product("Empty", "Unknown", 5);

        // Act
        double result = strategy.calculate(product);

        // Assert
        assertEquals(0.0, result, TOLERANCE);
    }

    @Test
    @DisplayName("Should return zero when lifespan is zero")
    void shouldReturnZeroWhenLifespanIsZero() {
        // Arrange
        Material plastic = new Material("Plastic", 5.0, "High recyclability");
        plastic.setRecyclabilityScore(0.9);
        Product product = new Product("BadProduct", "Test", 0);
        product.addMaterial(plastic, 1.0);

        // Act
        double result = strategy.calculate(product);

        // Assert
        assertEquals(0.0, result, TOLERANCE);
    }

    @Test
    @DisplayName("Should return zero when lifespan is negative")
    void shouldReturnZeroWhenLifespanIsNegative() {
        // Arrange
        Material plastic = new Material("Plastic", 5.0, "High recyclability");
        plastic.setRecyclabilityScore(0.9);
        Product product = new Product("BadProduct", "Test", -1);
        product.addMaterial(plastic, 1.0);

        // Act
        double result = strategy.calculate(product);

        // Assert
        assertEquals(0.0, result, TOLERANCE);
    }

    @Test
    @DisplayName("Should calculate weighted impact for single high-recyclability material")
    void shouldCalculateWeightedImpactForSingleHighRecyclabilityMaterial() {
        // Arrange
        Material plastic = new Material("Plastic", 5.0, "High recyclability");
        plastic.setRecyclabilityScore(0.9); // Explicitly setting the score
        Product bottle = new Product("Bottle", "Container", 5);
        bottle.addMaterial(plastic, 2.0);

        // Act
        double result = strategy.calculate(bottle);

        // Assert
        double expected = (2.0 * 5.0 / 5) * (1.0 - 0.9 * DELTA);
        assertEquals(expected, result, TOLERANCE);
    }

    @Test
    @DisplayName("Should calculate weighted impact for single low-recyclability material")
    void shouldCalculateWeightedImpactForSingleLowRecyclabilityMaterial() {
        // Arrange
        Material styrofoam = new Material("Styrofoam", 10.0, "Low recyclability");
        styrofoam.setRecyclabilityScore(0.2); // Explicitly setting the score
        Product cup = new Product("Cup", "Container", 2);
        cup.addMaterial(styrofoam, 0.1);

        // Act
        double result = strategy.calculate(cup);

        // Assert
        double expected = (0.1 * 10.0 / 2) * (1.0 - 0.2 * DELTA);
        assertEquals(expected, result, TOLERANCE);
    }

    @Test
    @DisplayName("Should calculate weighted impact for mixed materials")
    void shouldCalculateWeightedImpactForMixedMaterials() {
        // Arrange
        Material plastic = new Material("Plastic", 5.0, "High recyclability");
        plastic.setRecyclabilityScore(0.9);
        Material metal = new Material("Metal", 3.0, "Medium recyclability");
        metal.setRecyclabilityScore(0.6);
        
        Product phone = new Product("Phone", "Electronics", 4);
        phone.addMaterial(plastic, 0.1);
        phone.addMaterial(metal, 0.2);

        // Act
        double result = strategy.calculate(phone);

        // Assert
        double expectedPcfAnnual = 1.1 / 4;
        double expectedWeightedRecyclability = (0.1/0.3) * 0.9 + (0.2/0.3) * 0.6;
        double expectedCredit = 1.0 - (expectedWeightedRecyclability * DELTA);
        double expected = expectedPcfAnnual * expectedCredit;
        assertEquals(expected, result, TOLERANCE);
    }

    @Test
    @DisplayName("Should apply correct recyclability score for medium recyclability")
    void shouldApplyCorrectRecyclabilityScoreForMediumRecyclability() {
        // Arrange
        Material paper = new Material("Paper", 1.0, "Medium recyclability");
        paper.setRecyclabilityScore(0.6); // Explicitly setting the score
        Product notebook = new Product("Notebook", "Stationery", 1);
        notebook.addMaterial(paper, 0.5);

        // Act
        double result = strategy.calculate(notebook);

        // Assert
        double expected = (0.5 * 1.0 / 1) * (1.0 - 0.6 * DELTA);
        assertEquals(expected, result, TOLERANCE);
    }

    @Test
    @DisplayName("Should handle large lifespan reducing annual impact")
    void shouldHandleLargeLifespanReducingAnnualImpact() {
        // Arrange
        Material plastic = new Material("Plastic", 50.0, "High recyclability");
        plastic.setRecyclabilityScore(0.9); // Explicitly setting the score
        Product durable = new Product("Durable", "Equipment", 50);
        durable.addMaterial(plastic, 10.0);

        // Act
        double result = strategy.calculate(durable);

        // Assert
        double expected = (10.0 * 50.0 / 50) * (1.0 - 0.9 * DELTA);
        assertEquals(expected, result, TOLERANCE);
    }

    @Test
    @DisplayName("Should handle very small quantities")
    void shouldHandleVerySmallQuantities() {
        // Arrange
        Material plastic = new Material("Plastic", 5.0, "High recyclability");
        plastic.setRecyclabilityScore(0.9); // Explicitly setting the score
        Product tiny = new Product("Tiny", "Test", 1);
        tiny.addMaterial(plastic, 0.001);

        // Act
        double result = strategy.calculate(tiny);

        // Assert
        double expected = (0.001 * 5.0 / 1) * (1.0 - 0.9 * DELTA);
        assertEquals(expected, result, TOLERANCE);
    }
}