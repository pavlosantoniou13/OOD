package OOD.presentation;

import java.util.List;
import OOD.application.ImpactResult;
import OOD.application.ProductService;
import OOD.application.SimpleSumStrategy;
import OOD.application.WeightedStrategy;
import OOD.domain.MaterialQuantity;
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
            int choice = inputParser.readMenuChoice(1, 6);

            switch (choice) {
                case 1 -> {
                    productService.setImpactStrategy(new SimpleSumStrategy());
                    formatter.printSuccess("Calculation strategy updated to: Simple Sum Strategy.");
                }
                case 2 -> {
                    productService.setImpactStrategy(new WeightedStrategy());
                    formatter.printSuccess("Calculation strategy updated to: Weighted Lifespan Strategy.");
                }
                case 3 -> showImpactReport();
                case 4 -> showRecyclingGuidance();
                case 5 -> listAllProducts();
                case 6 -> back = true;
            }
        }
    }

    private void showOptions() {
        formatter.printHeader("Reports & Analytics Menu");
        formatter.printMenuOption(1, "Activate Simple Sum Strategy");
        formatter.printMenuOption(2, "Activate Weighted Lifespan Strategy");
        formatter.printMenuOption(3, "View Product Environmental Impact Report");
        formatter.printMenuOption(4, "View Product Recycling Guidance");
        formatter.printMenuOption(5, "List All Registered Product Details");
        formatter.printMenuOption(6, "Back to Main Menu");
        formatter.printDivider();
    }

    private void showImpactReport() {
        Product product = selectProduct();
        if (product == null) return;

        ImpactResult result = productService.getImpactReport(product);
        formatter.printHeader("Impact Report: " + result.productName());
        System.out.println("Computed Metrics Value: " + String.format("%.4f", result.value()));
        System.out.println("Environmental Severity Tier: " + result.severity());
        formatter.printDivider();
    }

    private void showRecyclingGuidance() {
        Product product = selectProduct();
        if (product == null) return;

        RecyclingGuidance guidance = productService.getRecyclingGuidance(product);
        formatter.printHeader("Recycling Guidelines: " + product.getName());
        System.out.println("Composition Archetype: " + (guidance.mixedMaterial() ? "Mixed Composition" : "Single Sub-component"));
        System.out.println(guidance.message());
        formatter.printDivider();
    }

    private void listAllProducts() {
        List<Product> products = productService.getAllProducts();
        formatter.printHeader("Registered Inventory Summary");

        if (products.isEmpty()) {
            formatter.printError("No product configurations registered within memory storage.");
            return;
        }

        for (Product product : products) {
            System.out.print(formatProductDetails(product));
            formatter.printDivider();
        }
    }

    private String formatProductDetails(Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append("System Tracking ID: ").append(product.getId()).append("\n");
        sb.append("Item Name: ").append(product.getName()).append("\n");
        sb.append("Classification Category: ").append(product.getCategory()).append("\n");
        sb.append("Expected Lifespan Metrics: ").append(product.getLifespanYears()).append(" operational years\n");
        sb.append(formatMaterials(product));
        sb.append(formatImpact(product));
        sb.append(formatRecycling(product));
        return sb.toString();
    }

    private String formatMaterials(Product product) {
        List<MaterialQuantity> materials = product.getMaterialQuantities();
        if (materials.isEmpty()) {
            return "Associated Component Mixtures: None\n";
        }

        StringBuilder sb = new StringBuilder("Associated Component Mixtures:\n");
        for (MaterialQuantity mq : materials) {
            sb.append(String.format("  - Component Element: %s (Mass Allocation: %.2f kg)\n", mq.material().getName(), mq.quantity()));
        }
        return sb.toString();
    }

    private String formatImpact(Product product) {
        ImpactResult impact = productService.getImpactReport(product);
        return String.format("Current Strategy Calculation: %.4f Rating Tier (%s)\n", impact.value(), impact.severity());
    }

    private String formatRecycling(Product product) {
        RecyclingGuidance guidance = productService.getRecyclingGuidance(product);
        return "Disposal Sorting Framework: " + (guidance.mixedMaterial() ? "Mixed-Material Breakdown" : "Single-Stream Allocation") + "\n";
    }

    private Product selectProduct() {
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            formatter.printError("No product entries populated. Initialize an entity profile first.");
            return null;
        }

        formatter.printHeader("Select Targeting Reference Profile");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " [" + p.getCategory() + "]");
        }
        formatter.printDivider();

        int choice = inputParser.readMenuChoice(1, products.size());
        return products.get(choice - 1);
    }
}