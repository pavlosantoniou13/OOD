package OOD.presentation;

import java.util.List;
import java.util.UUID;

import OOD.application.ImpactResult;
import OOD.application.ProductService;
import OOD.domain.Product;
import OOD.domain.RecyclingGuidance;

public class ReportMenu {
    private final ProductService productService;
    private final InputParser inputParser;
    private final OutputFormatter formatter;

    public ReportMenu(ProductService productService, InputParser inputParser, OutputFormatter formatter) {
        this.productService = productService;
        this.inputParser = inputParser;
        this.formatter = formatter;
    }

    public void show() {
        boolean back = false;

        while (!back) {
            showOptions();
            int choice = inputParser.readMenuChoice(1, 4);

            switch (choice) {
                case 1 -> showImpactReport();
                case 2 -> showRecyclingGuidance();
                case 3 -> listAllProducts();
                case 4 -> back = true;
            }
        }
    }

    private void showOptions() {
        formatter.printHeader("Reports Menu");
        formatter.printMenuOption(1, "Impact Report for Product");
        formatter.printMenuOption(2, "Recycling Guidance for Product");
        formatter.printMenuOption(3, "List All Product info");
        formatter.printMenuOption(4, "Back to Main Menu");
        formatter.printDivider();
    }

    private void showImpactReport() {
        Product product = selectProduct();
        if (product == null) return;

        ImpactResult result = productService.getImpactReport(product);
        formatter.printHeader("Impact Report: " + result.productName());
        System.out.println("Impact Value: " + String.format("%.4f", result.value()));
        System.out.println("Severity: " + result.severity());
        formatter.printDivider();
    }

    private void showRecyclingGuidance() {
        Product product = selectProduct();
        if (product == null) return;

        RecyclingGuidance guidance = productService.getRecyclingGuidance(product);
        formatter.printHeader("Recycling Guidance: " + product.getName());
        System.out.println("Material Type: " + (guidance.mixedMaterial() ? "Mixed Material" : "Single Material"));
        System.out.println(guidance.message());
        formatter.printDivider();
    }

    private void listAllProducts() {
        List<Product> products = productService.getAllProducts();
        formatter.printHeader("All Products");

        if (products.isEmpty()) {
            formatter.printError("No products registered.");
            return;
        }

        for (Product product : products) {
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Category: " + product.getCategory());
            System.out.println("Lifespan: " + product.getLifespanYears() + " years");

            List<OOD.domain.MaterialQuantity> materials = product.getMaterialQuantities();
            if (materials.isEmpty()) {
                System.out.println("Materials: None");
            } else {
                System.out.println("Materials:");
                for (OOD.domain.MaterialQuantity mq : materials) {
                    System.out.println("  - " + mq.material().getName() + 
                                       " (" + String.format("%.2f", mq.quantity()) + " kg)");
                }
            }

            ImpactResult impact = productService.getImpactReport(product);
            System.out.println("Impact: " + String.format("%.4f", impact.value()) + " (" + impact.severity() + ")");

            RecyclingGuidance guidance = productService.getRecyclingGuidance(product);
            System.out.println("Recycling: " + (guidance.mixedMaterial() ? "Mixed" : "Single"));

            formatter.printDivider();
        }
    }

    private Product selectProduct() {
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            formatter.printError("No products available. Create a product first.");
            return null;
        }

        formatter.printHeader("Select a Product");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (" + p.getCategory() + ")");
        }
        formatter.printDivider();

        int choice = inputParser.readMenuChoice(1, products.size());
        return products.get(choice - 1);
    }
}