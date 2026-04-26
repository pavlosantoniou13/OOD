package presentation;

public class ConsoleMenu {
    private final OutputFormatter formatter;
    private final InputParser inputParser;
    private final ProductMenu productMenu;
    private final MaterialMenu materialMenu;
    private final ReportMenu reportMenu;

    public ConsoleMenu(InputParser inputParser, OutputFormatter formatter, ProductMenu productMenu, MaterialMenu materialMenu, ReportMenu reportMenu) {
        this.formatter = formatter;
        this.inputParser = inputParser;
        this.productMenu = productMenu;
        this.materialMenu = materialMenu;
        this.reportMenu = reportMenu;
    }

    public void start() {
        boolean running = true;
        while (running) {
            showMainOptions();
            int choice = inputParser.readMenuChoice(1, 4);

            switch (choice) {
                case 1 -> productMenu.show();
                case 2 -> materialMenu.show();
                case 3 -> reportMenu.show();
                case 4 -> {
                    formatter.printSuccess("Goodbye!");
                    running = false;
                }
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
