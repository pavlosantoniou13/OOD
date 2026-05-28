package OOD.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecyclingGuidanceTest {

    @Test
    @DisplayName("Should verify record property mappings for empty composition")
    void shouldVerifyRecordPropertyMappingsForEmptyComposition() {
        // Arrange & Act
        RecyclingGuidance guidance = new RecyclingGuidance(false, "No materials registered for this product.");

        // Assert
        assertFalse(guidance.mixedMaterial());
        assertEquals("No materials registered for this product.", guidance.message());
    }

    @Test
    @DisplayName("Should verify record property mappings for composite mixtures")
    void shouldVerifyRecordPropertyMappingsForCompositeMixtures() {
        // Arrange & Act
        RecyclingGuidance guidance = new RecyclingGuidance(true, "Mixed-material product text.");

        // Assert
        assertTrue(guidance.mixedMaterial());
        assertEquals("Mixed-material product text.", guidance.message());
    }
}