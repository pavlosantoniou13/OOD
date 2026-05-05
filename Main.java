import application.*;
import domain.ImpactStrategy;
import presentation.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InMemoryProductRepository productRepo = new InMemoryProductRepository();
        InMemoryMaterialRepository materialRepo = new InMemoryMaterialRepository();

        ImpactStrategy impactStrategy = new SimpleSumStrategy();
        ProductService productService = new ProductService(productRepo, materialRepo, impactStrategy);
        MaterialService materialService = new MaterialService(materialRepo);

        Scanner scanner = new Scanner(System.in); 
        OutputFormatter formatter = new OutputFormatter();
        
        InputParser inputParser = new InputParser(scanner, formatter);

        ProductMenu productMenu = new ProductMenu(productService, materialService, inputParser, formatter);
        MaterialMenu materialMenu = new MaterialMenu(materialService, inputParser, formatter);
        ReportMenu reportMenu = new ReportMenu(productService, inputParser, formatter);

        ConsoleMenu menu = new ConsoleMenu(inputParser, formatter, productMenu, materialMenu, reportMenu);
        menu.start();
    }
}