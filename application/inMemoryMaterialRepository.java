package application;
import java.util.ArrayList;
import java.util.List;

import domain.*;

public class inMemoryMaterialRepository implements materialRepository {
    private final ArrayList<Material> storage = new ArrayList<>();

    @Override
    public void save(Material material) {
        storage.add(material);
    }

    @Override
    public List<Material> findAll() {
        return new ArrayList<>(storage);
    }
}
