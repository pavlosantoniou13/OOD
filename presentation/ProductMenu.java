package presentation;

import application.ProductService;
import application.MaterialService;

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
        // TODO
    }

    private void listProducts() {
        // TODO

    }
}