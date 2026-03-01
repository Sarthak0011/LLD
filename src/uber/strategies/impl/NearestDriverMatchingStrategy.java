package uber.strategies.impl;

import uber.entities.Driver;
import uber.entities.TripMetaData;
import uber.enums.DriverStatus;
import uber.managers.DriverManager;
import uber.strategies.DriverMatchingStrategy;

import java.util.List;

public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {

    @Override
    public Driver matchDriver(TripMetaData tripMetaData) {
        List<Driver> availableDrivers = DriverManager.getInstance().getAvailableDrivers();

        return availableDrivers.stream()
                .filter(driver -> driver.getDriverStatus() == DriverStatus.AVAILABLE)
                .filter(driver -> driver.getCurrentLocation() != null)
                .filter(driver -> driver.getVehicle() != null
                        && driver.getVehicle().getType() == tripMetaData.getRequestedVehicleType())
                .min((d1, d2) -> {
                    double dist1 = d1.getCurrentLocation().distance(tripMetaData.getSrc());
                    double dist2 = d2.getCurrentLocation().distance(tripMetaData.getSrc());
                    return Double.compare(dist1, dist2);
                })
                .orElse(null);
    }
}
