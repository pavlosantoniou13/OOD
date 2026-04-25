package application;

import java.util.List;

public record CreateProductRequest(
    String name,
    String category,
    int lifespanYears,
    List<MaterialSelection> materials) {
}