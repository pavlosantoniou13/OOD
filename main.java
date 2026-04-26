import application.*;
import presentation.*;

public class Main {
    public static void main(String[] args) {
        var repository = new InMemoryProductRepository();
        var service = new ProductService(repository);

        System.out.println("Walking skeleton!");
    }
}