package OOD.application;

import java.util.List;
import java.util.UUID;
import OOD.domain.*;

public class ProductService {
    private final ProductRepository repository;
    private ImpactStrategy impactStrategy;

    public ProductService(ProductRepository repository, ImpactStrategy impactStrategy) {
        this.repository = repository;
        this.impactStrategy = impactStrategy;
    }

    public void setImpactStrategy(ImpactStrategy strategy) {
        this.impactStrategy = strategy;
    }

    public void createProduct(String name, String category, int lifespan) {
        Product product = new Product(name, category, lifespan);
        repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product findById(UUID id) {
        return repository.findById(id);
    }

    public ImpactResult getImpactReport(Product product) {
        double impact = product.calculateImpact(impactStrategy);
        String severity;
        if (impact < 50) severity = "Low";
        else if (impact < 150) severity = "Moderate";
        else severity = "High";
        return new ImpactResult(product.getName(), impact, severity);
    }

    public RecyclingGuidance getRecyclingGuidance(Product product) {
        return product.getRecyclingGuidance();
    }
}