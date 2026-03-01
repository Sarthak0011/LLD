package uber.strategies.impl;

import uber.entities.TripMetaData;
import uber.enums.VehicleType;
import uber.strategies.PricingStrategy;

public class DistanceBasedPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(TripMetaData tripMetaData) {
        double distance = tripMetaData.getSrc().distance(tripMetaData.getDest());
        VehicleType type = tripMetaData.getRequestedVehicleType();

        double ratePerKm;
        double minimumFare;

        switch (type) {
            case GO    -> { ratePerKm = 12.0; minimumFare = 40.0; }
            case SEDAN -> { ratePerKm = 18.0; minimumFare = 60.0; }
            case XUV   -> { ratePerKm = 25.0; minimumFare = 100.0; }
            default    -> { ratePerKm = 12.0; minimumFare = 40.0; }
        }

        return Math.max(distance * ratePerKm, minimumFare);
    }
}
