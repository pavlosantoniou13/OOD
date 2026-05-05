package presentation;

import java.util.Scanner;

public class InputParser {

    private final Scanner scanner;
    private final OutputFormatter formatter;

    public InputParser(Scanner scanner, OutputFormatter formatter) {
        this.scanner = scanner;
        this.formatter = formatter;
    }

    public String readString(String prompt) {
        formatter.printPrompt(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        while (true) {
            formatter.printPrompt(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                formatter.printError("Please enter a valid number.");
            }
        }
    }
    public double readDouble(String prompt) {
        while (true) {
            formatter.printPrompt(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                formatter.printError("Please enter a valid decimal number.");
            }
        }
    }

    public int readMenuChoice(int min, int max) {
        while (true) {
            int choice = readInt("Choice");
            if (choice >= min && choice <= max) {
                return choice;
            }
            formatter.printError("Please enter a number between " + min + " and " + max + ".");
        }
    }

}
