package OOD.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Material plastic;
    private Material metal;

    @BeforeEach
    void setUp() {
        plastic = new Material("Plastic", 5.0, "High recyclability");
        metal = new Material("Metal", 3.0, "Medium recyclability");
    }

    @Test
    @DisplayName("Should create product with correct initial values")
    void shouldCreateProductWithCorrectInitialValues() {
        Product product = new Product("Bottle", "Container", 24);

        assertEquals("Bottle", product.getName());
        assertEquals("Container", product.getCategory());
        assertEquals(24, product.getLifespanYears());
        assertNotNull(product.getId());
        assertTrue(product.getMaterialQuantities().isEmpty());
    }

    @Test
    @DisplayName("Should add single material to product")
    void shouldAddSingleMaterialToProduct() {
        Product product = new Product("Bottle", "Container", 24);
        product.addMaterial(plastic, 0.5);

        List<MaterialQuantity> materials = product.getMaterialQuantities();
        assertEquals(1, materials.size());
        assertEquals("Plastic", materials.get(0).material().getName());
        assertEquals(0.5, materials.get(0).quantity(), 0.001);
    }

    @Test
    @DisplayName("Should add multiple materials to product")
    void shouldAddMultipleMaterialsToProduct() {
        Product product = new Product("Phone", "Electronics", 36);
        product.addMaterial(plastic, 0.1);
        product.addMaterial(metal, 0.2);

        List<MaterialQuantity> materials = product.getMaterialQuantities();
        assertEquals(2, materials.size());
    }

    @Test
    @DisplayName("Should delegate impact calculation to strategy")
    void shouldDelegateImpactCalculationToStrategy() {
        Product product = new Product("Bottle", "Container", 24);
        product.addMaterial(plastic, 1.0);

        ImpactStrategy fakeStrategy = p -> 42.0;

        double result = product.calculateImpact(fakeStrategy);
        assertEquals(42.0, result, 0.001);
    }

    @Test
    @DisplayName("Should update name via setter")
    void shouldUpdateNameViaSetter() {
        Product product = new Product("Old", "Category", 10);
        product.setName("New");
        assertEquals("New", product.getName());
    }

    @Test
    @DisplayName("Should update category via setter")
    void shouldUpdateCategoryViaSetter() {
        Product product = new Product("Name", "OldCat", 10);
        product.setCategory("NewCat");
        assertEquals("NewCat", product.getCategory());
    }

    @Test
    @DisplayName("Should update lifespan via setter")
    void shouldUpdateLifespanViaSetter() {
        Product product = new Product("Name", "Cat", 10);
        product.setLifespanYears(20);
        assertEquals(20, product.getLifespanYears());
    }

    @Test
    @DisplayName("Should generate unique IDs for different products")
    void shouldGenerateUniqueIdsForDifferentProducts() {
        Product product1 = new Product("A", "Cat", 1);
        Product product2 = new Product("B", "Cat", 2);
        assertNotEquals(product1.getId(), product2.getId());
    }

    @Test
    @DisplayName("Should return recycling guidance for no materials")
    void shouldReturnRecyclingGuidanceForNoMaterials() {
        Product product = new Product("Empty", "Test", 1);
        RecyclingGuidance guidance = product.getRecyclingGuidance();

        assertFalse(guidance.mixedMaterial());
        assertEquals("No materials registered for this product.", guidance.message());
    }

    @Test
    @DisplayName("Should return single material recycling guidance")
    void shouldReturnSingleMaterialRecyclingGuidance() {
        Product product = new Product("Bottle", "Container", 24);
        product.addMaterial(plastic, 0.5);
        RecyclingGuidance guidance = product.getRecyclingGuidance();

        assertFalse(guidance.mixedMaterial());
        assertTrue(guidance.message().contains("Single-material product"));
        assertTrue(guidance.message().contains("High recyclability"));
        assertTrue(guidance.message().contains("90%"));
    }

    @Test
    @DisplayName("Should return mixed material recycling guidance")
    void shouldReturnMixedMaterialRecyclingGuidance() {
        Product product = new Product("Phone", "Electronics", 36);
        product.addMaterial(plastic, 0.1);
        product.addMaterial(metal, 0.2);
        RecyclingGuidance guidance = product.getRecyclingGuidance();

        assertTrue(guidance.mixedMaterial());
        assertTrue(guidance.message().contains("Mixed-material product"));
        assertTrue(guidance.message().contains("Plastic"));
        assertTrue(guidance.message().contains("Metal"));
        assertTrue(guidance.message().contains("90%"));
        assertTrue(guidance.message().contains("60%"));
    }
}