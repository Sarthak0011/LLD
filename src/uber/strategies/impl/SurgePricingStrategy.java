package uber.strategies.impl;

import uber.entities.TripMetaData;
import uber.strategies.PricingStrategy;

public class SurgePricingStrategy implements PricingStrategy {

    private final PricingStrategy baseStrategy;
    private final double surgeMultiplier;

    public SurgePricingStrategy(PricingStrategy baseStrategy, double surgeMultiplier) {
        this.baseStrategy = baseStrategy;
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public double calculatePrice(TripMetaData tripMetaData) {
        return baseStrategy.calculatePrice(tripMetaData) * surgeMultiplier;
    }
}
