package application;

import domain.Material;
import domain.MaterialRepository;
import java.util.List;
import java.util.UUID;


public class MaterialService {
    private final MaterialRepository repository;

    public MaterialService(MaterialRepository repository) {
        this.repository = repository;
    }

    public void addMaterial(String name, double impactValue, String recyclingInstruction) {
        Material material = new Material(name, impactValue, recyclingInstruction);
        repository.save(material);
    }

    public List<Material> getAllMaterials() {
        return repository.findAll();
    }
}
