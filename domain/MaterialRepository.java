package domain;
import java.util.List;
import java.util.UUID;


public interface MaterialRepository {
    void save(Material material);
    List<Material> findAll();
    Material findById(UUID id);
}
