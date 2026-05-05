package application;
import java.util.List;
import domain.*;

public class ProductService {
    // Reference of interface so the Service can use a test repo later on
    private final ProductRepository repository;
    private final MaterialRepository materialRepository;
    private final ImpactStrategy impactStrategy;

    // Inject for dependency inversion
    public ProductService(ProductRepository repository, MaterialRepository materialRepository, ImpactStrategy impactStrategy) {
        this.repository = repository;
        this.materialRepository = materialRepository;
        this.impactStrategy = impactStrategy;
    }

    public void createProduct(String name, String category, int lifespan) {
        Product product = new Product(name, category, lifespan);
        repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
