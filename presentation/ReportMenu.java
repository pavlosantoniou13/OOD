package presentation;

import application.ProductService;

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
        //TODO
    }

    private void showRecyclingGuidance() {
        //TODO
    }

    private void listAllProducts() {
        //TODO
    }

    private UUID selectProduct() {
        //TODO
    }
}