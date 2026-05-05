package application;

import java.util.UUID;

public record MaterialSelection(
    UUID materialId,
    double quantity
) {
    
}
