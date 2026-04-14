package application;
import java.util.List;
import domain.*;

public class ProductService {
    // Reference of interface so the Service can use a test repo later on
    private final ProductRepository repository;

    // Inject for dependency inversion
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void createProduct(String name, String category, int lifespan) {
        Product product = new Product(name);
        repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
