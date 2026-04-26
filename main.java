import application.InMemoryMaterialRepository;   // ← make sure this import exists
import application.InMemoryProductRepository;
import application.MaterialService;
import application.ProductService;
import presentation.ConsoleMenu;

public class main {
    public static void main(String[] args) {
        // Wire repositories
        InMemoryProductRepository productRepo = new InMemoryProductRepository();
        InMemoryMaterialRepository materialRepo = new InMemoryMaterialRepository();  // ← FIXED

        // Wire services
        ProductService productService = new ProductService(productRepo);
        MaterialService materialService = new MaterialService(materialRepo);

        // Wire menu
        ConsoleMenu menu = new ConsoleMenu(productService, materialService);
        menu.start();
    }
}