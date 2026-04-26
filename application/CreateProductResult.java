package application;

import java.util.UUID;

public record CreateProductResult(
    UUID productId,
    String displayName,
    double estimatedImpact,
    String recyclingSummary) {
    
}
