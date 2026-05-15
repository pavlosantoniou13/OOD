package OOD.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Material domain class.
 * Tests cover: construction, getters, recyclability score parsing, and setters.
 */
public class MaterialTest {

    @Test
    @DisplayName("Should create material with correct name and impact value")
    void shouldCreateMaterialWithCorrectNameAndImpactValue() {
        // Arrange & Act
        Material material = new Material("Plastic", 5.0, "High recyclability");

        // Assert
        assertEquals("Plastic", material.getName());
        assertEquals(5.0, material.getEnvironmentalImpactValue(), 0.001);
        assertNotNull(material.getId());
    }

    @Test
    @DisplayName("Should parse high recyclability score correctly")
    void shouldParseHighRecyclabilityScoreCorrectly() {
        // Arrange & Act
        Material material = new Material("Glass", 2.0, "High recyclability");

        // Assert
        assertEquals(0.9, material.getRecyclabilityScore(), 0.001);
    }

    @Test
    @DisplayName("Should parse medium recyclability score correctly")
    void shouldParseMediumRecyclabilityScoreCorrectly() {
        // Arrange & Act
        Material material = new Material("Paper", 1.0, "Medium recyclability");

        // Assert
        assertEquals(0.6, material.getRecyclabilityScore(), 0.001);
    }

    @Test
    @DisplayName("Should parse low recyclability score correctly")
    void shouldParseLowRecyclabilityScoreCorrectly() {
        // Arrange & Act
        Material material = new Material("Styrofoam", 10.0, "Low recyclability");

        // Assert
        assertEquals(0.2, material.getRecyclabilityScore(), 0.001);
    }

    @Test
    @DisplayName("Should return default recyclability for unknown instruction")
    void shouldReturnDefaultRecyclabilityForUnknownInstruction() {
        // Arrange & Act
        Material material = new Material("Unknown", 3.0, "Some random text");

        // Assert
        assertEquals(0.5, material.getRecyclabilityScore(), 0.001);
    }

    @Test
    @DisplayName("Should update name via setter")
    void shouldUpdateNameViaSetter() {
        // Arrange
        Material material = new Material("OldName", 5.0, "Recycle");

        // Act
        material.setName("NewName");

        // Assert
        assertEquals("NewName", material.getName());
    }

    @Test
    @DisplayName("Should update impact value via setter")
    void shouldUpdateImpactValueViaSetter() {
        // Arrange
        Material material = new Material("Plastic", 5.0, "Recycle");

        // Act
        material.setEnvironmentalImpactValue(7.5);

        // Assert
        assertEquals(7.5, material.getEnvironmentalImpactValue(), 0.001);
    }

    @Test
    @DisplayName("Should update recycling instruction and recalculate score")
    void shouldUpdateRecyclingInstructionAndRecalculateScore() {
        // Arrange
        Material material = new Material("Plastic", 5.0, "Low recyclability");
        assertEquals(0.2, material.getRecyclabilityScore(), 0.001);

        // Act
        material.setRecyclingInstruction("High recyclability");

        // Assert
        assertEquals("High recyclability", material.getRecyclingInstruction());
        assertEquals(0.9, material.getRecyclabilityScore(), 0.001);
    }

    @Test
    @DisplayName("Should generate unique IDs for different materials")
    void shouldGenerateUniqueIdsForDifferentMaterials() {
        // Arrange & Act
        Material material1 = new Material("A", 1.0, "Recycle");
        Material material2 = new Material("B", 2.0, "Recycle");

        // Assert
        assertNotEquals(material1.getId(), material2.getId());
    }
}