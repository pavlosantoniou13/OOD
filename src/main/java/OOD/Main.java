package OOD;
import OOD.application.*;
import OOD.domain.ImpactStrategy;
import OOD.presentation.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InMemoryProductRepository productRepo = new InMemoryProductRepository();
        InMemoryMaterialRepository materialRepo = new InMemoryMaterialRepository();

        ImpactStrategy impactStrategy = new SimpleSumStrategy();
        ProductService productService = new ProductService(productRepo, materialRepo, impactStrategy);
        MaterialService materialService = new MaterialService(materialRepo);

        Scanner scanner = new Scanner(System.in); 
        OOD.presentation.OutputFormatter formatter = new OOD.presentation.OutputFormatter();
        
        InputParser inputParser = new InputParser(scanner, formatter);

        ProductMenu productMenu = new ProductMenu(productService, materialService, inputParser, formatter);
        MaterialMenu materialMenu = new MaterialMenu(materialService, inputParser, formatter);
        ReportMenu reportMenu = new ReportMenu(productService, inputParser, formatter);

        ConsoleMenu menu = new ConsoleMenu(inputParser, formatter, productMenu, materialMenu, reportMenu);
        menu.start();
    }
}