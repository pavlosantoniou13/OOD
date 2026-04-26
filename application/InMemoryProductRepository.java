package application;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import domain.*;

public class InMemoryProductRepository implements ProductRepository {
    private final ArrayList<Product> storage = new ArrayList<>();

    @Override
    public void save(Product product) {
        storage.add(product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public Product findById(UUID id) {
        for(Product p : storage) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
