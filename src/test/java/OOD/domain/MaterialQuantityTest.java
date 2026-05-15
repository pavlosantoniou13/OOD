package OOD.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialQuantityTest {

    @Test
    @DisplayName("Should create material quantity with correct values")
    void shouldCreateMaterialQuantityWithCorrectValues() {
        // Arrange
        Material plastic = new Material("Plastic", 5.0, "High recyclability");

        // Act
        MaterialQuantity mq = new MaterialQuantity(plastic, 2.5);

        // Assert
        assertEquals("Plastic", mq.material().getName());
        assertEquals(5.0, mq.material().getEnvironmentalImpactValue(), 0.001);
        assertEquals(2.5, mq.quantity(), 0.001);
    }

    @Test
    @DisplayName("Should handle zero quantity")
    void shouldHandleZeroQuantity() {
        // Arrange
        Material glass = new Material("Glass", 2.5, "High recyclability");

        // Act
        MaterialQuantity mq = new MaterialQuantity(glass, 0.0);

        // Assert
        assertEquals(0.0, mq.quantity(), 0.001);
    }

    @Test
    @DisplayName("Should handle negative quantity")
    void shouldHandleNegativeQuantity() {
        // Arrange
        Material metal = new Material("Metal", 3.0, "Medium recyclability");

        // Act
        MaterialQuantity mq = new MaterialQuantity(metal, -1.0);

        // Assert
        assertEquals(-1.0, mq.quantity(), 0.001);
    }

    @Test
    @DisplayName("Should be equal for same material and quantity")
    void shouldBeEqualForSameMaterialAndQuantity() {
        // Arrange
        Material plastic = new Material("Plastic", 5.0, "High recyclability");
        MaterialQuantity mq1 = new MaterialQuantity(plastic, 1.0);
        MaterialQuantity mq2 = new MaterialQuantity(plastic, 1.0);

        // Act & Assert
        assertEquals(mq1, mq2);
        assertEquals(mq1.hashCode(), mq2.hashCode());
    }
}