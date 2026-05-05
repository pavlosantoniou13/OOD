package application;

import domain.ImpactStrategy;
import domain.Product;

public class SimpleSumStrategy implements ImpactStrategy {

    @Override
    public double calculate(Product product) {
        double total = 0.0;
        
        for (domain.MaterialQuantity mq : product.getMaterialQuantities()) {
            total += mq.quantity() * mq.material().getEnvironmentalImpactValue();
        }
        
        return total;
    }
}