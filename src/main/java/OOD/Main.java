package OOD;

import OOD.application.*;
import OOD.domain.ImpactStrategy;
import OOD.domain.Material;
import OOD.domain.Product;
import OOD.presentation.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InMemoryProductRepository productRepo = new InMemoryProductRepository();
        InMemoryMaterialRepository materialRepo = new InMemoryMaterialRepository();

        
        PremadeLists(productRepo, materialRepo);

        ImpactStrategy defaultStrategy = new SimpleSumStrategy();
        ProductService productService = new ProductService(productRepo, defaultStrategy);
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

    private static void PremadeLists(InMemoryProductRepository productRepo, InMemoryMaterialRepository materialRepo) {
        // 1. Save premade materials into the material repository storage
        materialRepo.save(new Material("Virgin Aluminum", 12.0, "High recyclability classification loop"));
        materialRepo.save(new Material("Recycled Aluminum", 1.5, "High recyclability optimization trace"));
        materialRepo.save(new Material("Virgin Steel", 2.2, "High recyclability framework allocation"));
        materialRepo.save(new Material("Recycled Steel", 0.4, "High recyclability closed loop asset"));
        materialRepo.save(new Material("PET Plastic", 3.5, "Medium recyclability industrial stream"));
        materialRepo.save(new Material("HDPE Plastic", 2.0, "Medium recyclability resource recovery"));
        materialRepo.save(new Material("PVC Plastic", 2.1, "Low recyclability hazardous isolation sorting"));
        materialRepo.save(new Material("Virgin Glass", 1.1, "High recyclability mineral tracking"));
        materialRepo.save(new Material("Recycled Glass", 0.64, "High recyclability melt batch allocation"));
        materialRepo.save(new Material("Wood (class 2)", 0.31, "Low recyclability organic processing flow"));
        materialRepo.save(new Material("Paper (recycled/board)", 0.50, "Medium recyclability repulping facility"));
        materialRepo.save(new Material("Cotton (fabric)", 5.5, "Low recyclability post-consumer breakdown"));
        materialRepo.save(new Material("Natural Rubber", 1.3, "Low recyclability specialized parsing grid"));

        // 2. Build premade products
        Product aluminumCan = new Product("Aluminum Can", "Packaging", 2);
        aluminumCan.addMaterial(findMaterialByName(materialRepo, "Recycled Aluminum"), 0.05);
        productRepo.save(aluminumCan);

        Product Drone = new Product("Drone", "Electronics", 4);
        Drone.addMaterial(findMaterialByName(materialRepo, "Virgin Aluminum"), 2.5);
        Drone.addMaterial(findMaterialByName(materialRepo, "Virgin Steel"), 1.1);
        Drone.addMaterial(findMaterialByName(materialRepo, "PET Plastic"), 0.8);
        productRepo.save(Drone);
    }

    // Scan for duplicates
    private static Material findMaterialByName(InMemoryMaterialRepository repository, String name) {
        for (Material material : repository.findAll()) {
            if (material.getName().equalsIgnoreCase(name)) {
                return material;
            }
        }
        return null;
    }
}