package domain;

import java.util.List;
import java.util.UUID;


public interface ProductRepository {
    void save(Product product);
    List<Product> findAll();
    Product findById(UUID id);
}
