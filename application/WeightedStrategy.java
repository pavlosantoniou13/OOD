package application;

import domain.ImpactStrategy;
import domain.Product;

public class WeightedStrategy implements ImpactStrategy {

    private static final double DELTA = 0.15;

    @Override
    public double calculate(Product product) {
        double pcfRaw = 0.0;
        double totalMass = 0.0;

        for (domain.MaterialQuantity mq : product.getMaterialQuantities()) {
            double mass = mq.quantity();
            double ef = mq.material().getEnvironmentalImpactValue();
            pcfRaw += mass * ef;
            totalMass += mass;
        }

        if (totalMass == 0.0 || product.getLifespanYears() <= 0) {
            return 0.0;
        }

        double pcfAnnual = pcfRaw / product.getLifespanYears();

        double weightedRecyclability = 0.0;
        for (domain.MaterialQuantity mq : product.getMaterialQuantities()) {
            double mass = mq.quantity();
            double ri = mq.material().getRecyclabilityScore();
            weightedRecyclability += (mass / totalMass) * ri;
        }

        double credit = 1.0 - (weightedRecyclability * DELTA);
        return pcfAnnual * credit;
    }
}