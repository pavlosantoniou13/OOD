package presentation;

import application.MaterialService;
import application.ProductService;
import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner;
    private final OutputFormatter formatter;
    private final InputParser inputParser;
    private final ProductMenu productMenu;
    private final MaterialMenu materialMenu;
    private final ReportMenu reportMenu;

    public ConsoleMenu(ProductService productService, MaterialService materialService) {
        this.scanner = new Scanner(System.in);
        this.formatter = new OutputFormatter();
        this.inputParser = new InputParser(scanner, formatter);
        this.productMenu = new ProductMenu();
        this.materialMenu = new MaterialMenu();
        this.reportMenu = new ReportMenu();

    }

    public void start() {
        boolean running = true;
        while (running) {
            showMainOptions();
            int choice = inputParser.readMenuChoice(1, 4);

            switch (choice) {
                // needs to be implemented
            }
        }
    }

    private void showMainOptions() {
        formatter.printHeader("Main menu");
        formatter.printMenuOption(1, "Products");
        formatter.printMenuOption(2, "Materials");
        formatter.printMenuOption(3, "Reports");
        formatter.printMenuOption(4, "Exit");
        formatter.printDivider();
    }
}
