package OOD.application;

import OOD.domain.ImpactStrategy;
import OOD.domain.Product;

public class SimpleSumStrategy implements ImpactStrategy {

    @Override
    public double calculate(Product product) {
        double total = 0.0;
        
        for (OOD.domain.MaterialQuantity mq : product.getMaterialQuantities()) {
            total += mq.quantity() * mq.material().getEnvironmentalImpactValue();
        }
        
        return total;
    }
}