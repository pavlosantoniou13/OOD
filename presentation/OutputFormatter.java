package presentation;

public class OutputFormatter {
    public void printHeader(String title) {
        System.out.println("\n=== " + title + " ===");
    }

    public void printMenuOption(int number, String description) {
        System.out.println(number + ". " + description);
    }

    public void printSuccess(String message) {
        System.out.println("\n" + message);
    }

    public void printError(String message) {
        System.out.println("\nError: " + message);
    }

    public void printDivider() {
        System.out.println("------------------------");
    }
}
