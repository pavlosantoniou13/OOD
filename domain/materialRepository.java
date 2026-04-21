package domain;
import java.util.List;

public interface materialRepository {
    void save(Material material);
    List<Material> findAll();
}
