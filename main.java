import application.*;
import presentation.*;

public class main {
    public static void main(String[] args) {
        var repository = new InMemoryProductRepository();
        var service = new ProductService(repository);
        var menu = new ConsoleMenu();

        menu.start();
        System.out.println("Walking skeleton!");
    }
}