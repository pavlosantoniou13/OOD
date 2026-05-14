package OOD.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImpactResultTest {

    @Test
    @DisplayName("Should create impact result with correct values")
    void shouldCreateImpactResultWithCorrectValues() {
        // Arrange & Act
        ImpactResult result = new ImpactResult("Bottle", 75.5, "Moderate");

        // Assert
        assertEquals("Bottle", result.productName());
        assertEquals(75.5, result.value(), 0.001);
        assertEquals("Moderate", result.severity());
    }

    @Test
    @DisplayName("Should create impact result with zero value")
    void shouldCreateImpactResultWithZeroValue() {
        // Arrange & Act
        ImpactResult result = new ImpactResult("Empty", 0.0, "Low");

        // Assert
        assertEquals(0.0, result.value(), 0.001);
        assertEquals("Low", result.severity());
    }

    @Test
    @DisplayName("Should create impact result with high value")
    void shouldCreateImpactResultWithHighValue() {
        // Arrange & Act
        ImpactResult result = new ImpactResult("Hazardous", 999.99, "High");

        // Assert
        assertEquals(999.99, result.value(), 0.001);
        assertEquals("High", result.severity());
    }

    @Test
    @DisplayName("Should be equal for same values")
    void shouldBeEqualForSameValues() {
        // Arrange
        ImpactResult result1 = new ImpactResult("Bottle", 50.0, "Moderate");
        ImpactResult result2 = new ImpactResult("Bottle", 50.0, "Moderate");

        // Act & Assert
        assertEquals(result1, result2);
        assertEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal for different values")
    void shouldNotBeEqualForDifferentValues() {
        // Arrange
        ImpactResult result1 = new ImpactResult("Bottle", 50.0, "Moderate");
        ImpactResult result2 = new ImpactResult("Bottle", 51.0, "Moderate");

        // Act & Assert
        assertNotEquals(result1, result2);
    }

    @Test
    @DisplayName("Should generate meaningful toString")
    void shouldGenerateMeaningfulToString() {
        // Arrange
        ImpactResult result = new ImpactResult("Bottle", 75.5, "Moderate");

        // Act
        String str = result.toString();

        // Assert
        assertTrue(str.contains("Bottle"));
        assertTrue(str.contains("75.5") || str.contains("75,5"));  // locale dependent
    }
}