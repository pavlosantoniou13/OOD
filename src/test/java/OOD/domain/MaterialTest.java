package OOD.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaterialTest {

    @Test
    @DisplayName("Should preserve instantiation arguments through domain model parameters")
    void shouldPreserveInstantiationArgumentsThroughDomainModelParameters() {
        // Arrange
        String expectedName = "Tempered Glass";
        double expectedImpact = 2.45;
        String expectedInstruction = "Dispose inside designated clear glass bins.";

        // Act
        Material material = new Material(expectedName, expectedImpact, expectedInstruction);

        // Assert
        assertEquals(expectedName, material.getName());
        assertEquals(expectedImpact, material.getEnvironmentalImpactValue(), 0.001);
        assertEquals(expectedInstruction, material.getRecyclingInstruction());
    }
}