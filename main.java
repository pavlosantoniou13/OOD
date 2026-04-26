import application.*;
import domain.ImpactStrategy;
import presentation.*;

public class Main {
    public static void main(String[] args) {
        // Wire dependencies (Constructor Injection)
        InMemoryProductRepository productRepo = new InMemoryProductRepository();
        InMemoryMaterialRepository materialRepo = new InMemoryMaterialRepository();

        ImpactStrategy  impactStrategy = new SimpleSumStrategy();

        ProductService productService = new ProductService(productRepo, materialRepo, impactStrategy);
        MaterialService materialService = new MaterialService(materialRepo);

        ConsoleMenu menu = new ConsoleMenu(productService, materialService);
        menu.start();
    }
}