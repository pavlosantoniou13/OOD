package OOD.presentation;

import OOD.application.ProductService;
import OOD.domain.Material;
import OOD.domain.Product;
import OOD.application.MaterialService;

import java.util.List;

public class ProductMenu {
    private final ProductService productService;
    private final MaterialService materialService;
    private final InputParser inputParser;
    private final OutputFormatter formatter;

    public ProductMenu(ProductService productService, MaterialService materialService, InputParser inputParser, OutputFormatter formatter) {
        this.productService = productService;
        this.materialService = materialService;
        this.inputParser = inputParser;
        this.formatter = formatter;
    }

    public void show() {
        boolean back = false;
        while (!back) {
            showOptions();
            int choice = inputParser.readMenuChoice(1, 3);

            switch (choice) {
                case 1 -> createProduct();
                case 2 -> listProducts();
                case 3 -> back = true;
            }
        }
    }

    private void showOptions() {
        formatter.printHeader("Product Menu");
        formatter.printMenuOption(1, "Create Product");
        formatter.printMenuOption(2, "List Products");
        formatter.printMenuOption(3, "Back to Main Menu");
        formatter.printDivider();
    }

    private void createProduct() {
        String name = inputParser.readString("Enter product name");
        String category = inputParser.readString("Enter product category");
        int lifespan = inputParser.readInt("Enter estimated lifespan (years)");

        List<Material> availableMaterials = materialService.getAllMaterials();
        if (availableMaterials.isEmpty()) {
            formatter.printError("No materials available. Create materials first.");
            return;
        }

        // Create the product first
        productService.createProduct(name, category, lifespan);

        // Get the last created product (since we don't have createProduct returning the product)
        List<Product> allProducts = productService.getAllProducts();
        Product product = allProducts.get(allProducts.size() - 1);

        // Add materials
        boolean addingMaterials = true;
        while (addingMaterials) {
            formatter.printHeader("Add Material to Product");
            for (int i = 0; i < availableMaterials.size(); i++) {
                Material m = availableMaterials.get(i);
                System.out.println((i + 1) + ". " + m.getName() + " (Impact: " + m.getEnvironmentalImpactValue() + ")");
            }
            System.out.println((availableMaterials.size() + 1) + ". Done adding materials");
            formatter.printDivider();

            int materialChoice = inputParser.readMenuChoice(1, availableMaterials.size() + 1);

            if (materialChoice == availableMaterials.size() + 1) {
                addingMaterials = false;
            } else {
                Material selectedMaterial = availableMaterials.get(materialChoice - 1);
                double quantity = inputParser.readDouble("Enter quantity (kg) for " + selectedMaterial.getName());
                product.addMaterial(selectedMaterial, quantity);
                formatter.printSuccess("Added " + selectedMaterial.getName() + " (" + quantity + " kg) to product.");
            }
        }

        formatter.printSuccess(name + " created successfully with " + product.getMaterialQuantities().size() + " material(s).");
    }

    private void listProducts() {
        List<Product> products = productService.getAllProducts();
        formatter.printHeader("Products List");

        if (products.isEmpty()) {
            formatter.printError("No products registered.");
            return;
        }

        for (Product product : products) {
            System.out.println("Name: " + product.getName());
            System.out.println("Category: " + product.getCategory());
            System.out.println("Lifespan: " + product.getLifespanYears() + " years");
            System.out.println("Materials: " + product.getMaterialQuantities().size());
            formatter.printDivider();
        }

    }
}