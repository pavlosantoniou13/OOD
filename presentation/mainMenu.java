package presentation;

import application.ProductService;
import java.util.Scanner;

public class mainMenu {
    Scanner scanner = new Scanner(System.in);
    public void start() {
        System.out.println("--- Menu ---");
        System.out.println("1. Create Product (Stub)");
        System.out.println("2. Exit");
    }
}
