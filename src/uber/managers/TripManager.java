package uber.managers;

import uber.entities.Driver;
import uber.entities.Location;
import uber.entities.Rider;
import uber.entities.Trip;
import uber.entities.TripMetaData;
import uber.enums.DriverStatus;
import uber.enums.TripStatus;
import uber.enums.VehicleType;
import uber.strategies.DriverMatchingStrategy;
import uber.strategies.PricingStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TripManager {
    private static volatile TripManager instance;
    private final Map<String, Trip> activeTrips;
    private DriverMatchingStrategy driverMatchingStrategy;
    private PricingStrategy pricingStrategy;

    private TripManager() {
        this.activeTrips = new ConcurrentHashMap<>();
    }

    public static TripManager getInstance() {
        if (instance == null) {
            synchronized (TripManager.class) {
                if (instance == null) {
                    instance = new TripManager();
                }
            }
        }
        return instance;
    }

    public void setDriverMatchingStrategy(DriverMatchingStrategy driverMatchingStrategy) {
        this.driverMatchingStrategy = driverMatchingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public Trip createTrip(Rider rider, Location src, Location dest, VehicleType vehicleType) {
        TripMetaData tripMetaData = new TripMetaData(null, rider, src, dest, vehicleType);
        Driver driver = driverMatchingStrategy.matchDriver(tripMetaData);

        if (driver == null) {
            throw new RuntimeException("No available " + vehicleType + " driver found");
        }

        driver.updateStatus(DriverStatus.BUSY);

        Trip trip = new Trip(rider, driver, src, dest, TripStatus.CREATED, vehicleType);
        activeTrips.put(trip.getId(), trip);
        return trip;
    }

    public void startTrip(String tripId) {
        Trip trip = getTrip(tripId);
        trip.updateStatus(TripStatus.IN_PROGRESS);
    }

    public double endTrip(String tripId) {
        Trip trip = getTrip(tripId);
        trip.updateStatus(TripStatus.COMPLETED);

        Driver driver = trip.getDriver();
        driver.updateStatus(DriverStatus.AVAILABLE);

        TripMetaData tripMetaData = new TripMetaData(driver, trip.getRider(), trip.getSrc(), trip.getDest(), trip.getVehicleType());
        double price = pricingStrategy.calculatePrice(tripMetaData);

        activeTrips.remove(tripId);
        return price;
    }

    public void cancelTrip(String tripId) {
        Trip trip = getTrip(tripId);
        trip.updateStatus(TripStatus.CANCELLED);

        trip.getDriver().updateStatus(DriverStatus.AVAILABLE);
        activeTrips.remove(tripId);
    }

    public Trip getTrip(String tripId) {
        Trip trip = activeTrips.get(tripId);
        if (trip == null) {
            throw new RuntimeException("Trip not found: " + tripId);
        }
        return trip;
    }
}
