package OOD.presentation;

import java.util.List;

import OOD.application.ImpactResult;
import OOD.application.ProductService;
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

    // REFACTORED: Now delegates formatting to extracted methods
    private void listAllProducts() {
        List<Product> products = productService.getAllProducts();
        formatter.printHeader("All Products");

        if (products.isEmpty()) {
            formatter.printError("No products registered.");
            return;
        }

        for (Product product : products) {
            System.out.print(formatProductDetails(product));
            formatter.printDivider();
        }
    }

    // EXTRACTED METHOD: Formats a single product's details
    private String formatProductDetails(Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(product.getId()).append("");
        sb.append("Name: ").append(product.getName()).append("");
        sb.append("Category: ").append(product.getCategory()).append("");
        sb.append("Lifespan: ").append(product.getLifespanYears()).append(" years");
        sb.append(formatMaterials(product));
        sb.append(formatImpact(product));
        sb.append(formatRecycling(product));
        return sb.toString();
    }

    // EXTRACTED METHOD: Formats material list
    private String formatMaterials(Product product) {
        List<MaterialQuantity> materials = product.getMaterialQuantities();
        if (materials.isEmpty()) {
            return "Materials: None";
        }

        StringBuilder sb = new StringBuilder("Materials: ");
        for (MaterialQuantity mq : materials) {
            sb.append(String.format("  - %s (%.2f kg)",mq.material().getName(), mq.quantity()));
        }
        return sb.toString();
    }

    // EXTRACTED METHOD: Formats impact summary
    private String formatImpact(Product product) {
        ImpactResult impact = productService.getImpactReport(product);
        return String.format("Impact: %.4f (%s)", impact.value(), impact.severity());
    }

    // EXTRACTED METHOD: Formats recycling summary
    private String formatRecycling(Product product) {
        RecyclingGuidance guidance = productService.getRecyclingGuidance(product);
        return "Recycling: " + (guidance.mixedMaterial() ? "Mixed" : "Single") + "";
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