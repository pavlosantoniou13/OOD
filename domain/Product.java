package domain;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private List<Material> materials = new ArrayList<>();

    public Product(String name) {
        this.name = name; // Basic constructor for the skeleton
    }

}