package OOD.application;

import OOD.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private InMemoryProductRepository productRepo;
    private InMemoryMaterialRepository materialRepo;
    private ImpactStrategy fakeStrategy;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        // Arrange: fresh repositories and a predictable fake strategy for each test
        productRepo = new InMemoryProductRepository();
        materialRepo = new InMemoryMaterialRepository();
        fakeStrategy = product -> 100.0;  // Always returns 100.0 for predictability
        productService = new ProductService(productRepo, materialRepo, fakeStrategy);
    }

    @Test
    @DisplayName("Should create and save product")
    void shouldCreateAndSaveProduct() {
        // Act
        productService.createProduct("Bottle", "Container", 24);

        // Assert
        List<Product> all = productService.getAllProducts();
        assertEquals(1, all.size());
        assertEquals("Bottle", all.get(0).getName());
        assertEquals("Container", all.get(0).getCategory());
        assertEquals(24, all.get(0).getLifespanYears());
    }

    @Test
    @DisplayName("Should return empty list when no products")
    void shouldReturnEmptyListWhenNoProducts() {
        // Act
        List<Product> all = productService.getAllProducts();

        // Assert
        assertTrue(all.isEmpty());
    }

    @Test
    @DisplayName("Should find product by id")
    void shouldFindProductById() {
        // Arrange
        productService.createProduct("Phone", "Electronics", 36);
        Product saved = productService.getAllProducts().get(0);
        UUID id = saved.getId();

        // Act
        Product found = productService.findById(id);

        // Assert
        assertNotNull(found);
        assertEquals("Phone", found.getName());
    }

    @Test
    @DisplayName("Should return null when product id not found")
    void shouldReturnNullWhenProductIdNotFound() {
        // Arrange
        UUID randomId = UUID.randomUUID();

        // Act
        Product found = productService.findById(randomId);

        // Assert
        assertNull(found);
    }

    @Test
    @DisplayName("Should return low severity for impact below 50")
    void shouldReturnLowSeverityForImpactBelow50() {
        // Arrange
        ImpactStrategy lowImpactStrategy = product -> 25.0;
        ProductService service = new ProductService(productRepo, materialRepo, lowImpactStrategy);
        Product product = new Product("LowImpact", "Test", 1);

        // Act
        ImpactResult result = service.getImpactReport(product);

        // Assert
        assertEquals("LowImpact", result.productName());
        assertEquals(25.0, result.value(), 0.001);
        assertEquals("Low", result.severity());
    }

    @Test
    @DisplayName("Should return moderate severity for impact between 50 and 150")
    void shouldReturnModerateSeverityForImpactBetween50And150() {
        // Arrange
        ImpactStrategy moderateImpactStrategy = product -> 100.0;
        ProductService service = new ProductService(productRepo, materialRepo, moderateImpactStrategy);
        Product product = new Product("ModerateImpact", "Test", 1);

        // Act
        ImpactResult result = service.getImpactReport(product);

        // Assert
        assertEquals("ModerateImpact", result.productName());
        assertEquals(100.0, result.value(), 0.001);
        assertEquals("Moderate", result.severity());
    }

    @Test
    @DisplayName("Should return high severity for impact above 150")
    void shouldReturnHighSeverityForImpactAbove150() {
        // Arrange
        ImpactStrategy highImpactStrategy = product -> 200.0;
        ProductService service = new ProductService(productRepo, materialRepo, highImpactStrategy);
        Product product = new Product("HighImpact", "Test", 1);

        // Act
        ImpactResult result = service.getImpactReport(product);

        // Assert
        assertEquals("HighImpact", result.productName());
        assertEquals(200.0, result.value(), 0.001);
        assertEquals("High", result.severity());
    }

    @Test
    @DisplayName("Should return exact boundary low severity at 49.999")
    void shouldReturnExactBoundaryLowSeverityAt49_999() {
        // Arrange
        ImpactStrategy boundaryStrategy = product -> 49.999;
        ProductService service = new ProductService(productRepo, materialRepo, boundaryStrategy);
        Product product = new Product("Boundary", "Test", 1);

        // Act
        ImpactResult result = service.getImpactReport(product);

        // Assert
        assertEquals("Low", result.severity());
    }

    @Test
    @DisplayName("Should return exact boundary moderate severity at 50")
    void shouldReturnExactBoundaryModerateSeverityAt50() {
        // Arrange
        ImpactStrategy boundaryStrategy = product -> 50.0;
        ProductService service = new ProductService(productRepo, materialRepo, boundaryStrategy);
        Product product = new Product("Boundary", "Test", 1);

        // Act
        ImpactResult result = service.getImpactReport(product);

        // Assert
        assertEquals("Moderate", result.severity());
    }

    @Test
    @DisplayName("Should return exact boundary moderate severity at 149.999")
    void shouldReturnExactBoundaryModerateSeverityAt149_999() {
        // Arrange
        ImpactStrategy boundaryStrategy = product -> 149.999;
        ProductService service = new ProductService(productRepo, materialRepo, boundaryStrategy);
        Product product = new Product("Boundary", "Test", 1);

        // Act
        ImpactResult result = service.getImpactReport(product);

        // Assert
        assertEquals("Moderate", result.severity());
    }

    @Test
    @DisplayName("Should return exact boundary high severity at 150")
    void shouldReturnExactBoundaryHighSeverityAt150() {
        // Arrange
        ImpactStrategy boundaryStrategy = product -> 150.0;
        ProductService service = new ProductService(productRepo, materialRepo, boundaryStrategy);
        Product product = new Product("Boundary", "Test", 1);

        // Act
        ImpactResult result = service.getImpactReport(product);

        // Assert
        assertEquals("High", result.severity());
    }

    @Test
    @DisplayName("Should delegate recycling guidance to product")
    void shouldDelegateRecyclingGuidanceToProduct() {
        // Arrange
        Material plastic = new Material("Plastic", 5.0, "High recyclability");
        Product bottle = new Product("Bottle", "Container", 24);
        bottle.addMaterial(plastic, 0.5);

        // Act
        RecyclingGuidance guidance = productService.getRecyclingGuidance(bottle);

        // Assert
        assertNotNull(guidance);
        assertFalse(guidance.mixedMaterial());
        assertTrue(guidance.message().contains("Single-material product"));
    }

    @Test
    @DisplayName("Should handle multiple products")
    void shouldHandleMultipleProducts() {
        // Arrange
        productService.createProduct("Bottle", "Container", 24);
        productService.createProduct("Phone", "Electronics", 36);
        productService.createProduct("Chair", "Furniture", 120);

        // Act
        List<Product> all = productService.getAllProducts();

        // Assert
        assertEquals(3, all.size());
    }
}