package OOD.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecyclingGuidanceTest {

    @Test
    @DisplayName("Should create recycling guidance with correct values")
    void shouldCreateRecyclingGuidanceWithCorrectValues() {
        // Arrange & Act
        RecyclingGuidance guidance = new RecyclingGuidance(true, "Mixed materials, disassemble first.");

        // Assert
        assertTrue(guidance.mixedMaterial());
        assertEquals("Mixed materials, disassemble first.", guidance.message());
    }

    @Test
    @DisplayName("Should create single material guidance")
    void shouldCreateSingleMaterialGuidance() {
        // Arrange & Act
        RecyclingGuidance guidance = new RecyclingGuidance(false, "Recycle in paper bin.");

        // Assert
        assertFalse(guidance.mixedMaterial());
        assertEquals("Recycle in paper bin.", guidance.message());
    }

    @Test
    @DisplayName("Should handle empty message")
    void shouldHandleEmptyMessage() {
        // Arrange & Act
        RecyclingGuidance guidance = new RecyclingGuidance(false, "");

        // Assert
        assertFalse(guidance.mixedMaterial());
        assertEquals("", guidance.message());
    }

    @Test
    @DisplayName("Should be equal for same values")
    void shouldBeEqualForSameValues() {
        // Arrange
        RecyclingGuidance guidance1 = new RecyclingGuidance(true, "Test message");
        RecyclingGuidance guidance2 = new RecyclingGuidance(true, "Test message");

        // Act & Assert
        assertEquals(guidance1, guidance2);
        assertEquals(guidance1.hashCode(), guidance2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal for different mixedMaterial flag")
    void shouldNotBeEqualForDifferentMixedMaterialFlag() {
        // Arrange
        RecyclingGuidance guidance1 = new RecyclingGuidance(true, "Test message");
        RecyclingGuidance guidance2 = new RecyclingGuidance(false, "Test message");

        // Act & Assert
        assertNotEquals(guidance1, guidance2);
    }

    @Test
    @DisplayName("Should not be equal for different messages")
    void shouldNotBeEqualForDifferentMessages() {
        // Arrange
        RecyclingGuidance guidance1 = new RecyclingGuidance(true, "Message A");
        RecyclingGuidance guidance2 = new RecyclingGuidance(true, "Message B");

        // Act & Assert
        assertNotEquals(guidance1, guidance2);
    }

    @Test
    @DisplayName("Should generate meaningful toString")
    void shouldGenerateMeaningfulToString() {
        // Arrange
        RecyclingGuidance guidance = new RecyclingGuidance(true, "Test");

        // Act
        String str = guidance.toString();

        // Assert
        assertTrue(str.contains("mixedMaterial=true"));
        assertTrue(str.contains("Test"));
    }
}