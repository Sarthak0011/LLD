package uber;

import uber.entities.Driver;
import uber.entities.Location;
import uber.entities.Rider;
import uber.entities.Trip;
import uber.entities.Vehicle;
import uber.enums.DriverStatus;
import uber.enums.VehicleType;
import uber.managers.DriverManager;
import uber.managers.RiderManager;
import uber.managers.TripManager;
import uber.strategies.impl.DistanceBasedPricingStrategy;
import uber.strategies.impl.NearestDriverMatchingStrategy;
import uber.strategies.impl.SurgePricingStrategy;

public class Main {
    public static void main(String[] args) {
        DriverManager driverManager = DriverManager.getInstance();
        RiderManager riderManager = RiderManager.getInstance();
        TripManager tripManager = TripManager.getInstance();

        tripManager.setDriverMatchingStrategy(new NearestDriverMatchingStrategy());
        tripManager.setPricingStrategy(new DistanceBasedPricingStrategy());

        Driver goDriver = new Driver("Ramesh", "9000000001");
        goDriver.registerVehicle(new Vehicle("MH01AB1234", VehicleType.GO));
        goDriver.updateLocation(new Location(2, 2));
        goDriver.updateStatus(DriverStatus.AVAILABLE);
        driverManager.addDriver(goDriver);

        Driver sedanDriver = new Driver("Suresh", "9000000002");
        sedanDriver.registerVehicle(new Vehicle("MH02CD5678", VehicleType.SEDAN));
        sedanDriver.updateLocation(new Location(10, 10));
        sedanDriver.updateStatus(DriverStatus.AVAILABLE);
        driverManager.addDriver(sedanDriver);

        Driver xuvDriver = new Driver("Mahesh", "9000000003");
        xuvDriver.registerVehicle(new Vehicle("MH03EF9999", VehicleType.XUV));
        xuvDriver.updateLocation(new Location(5, 5));
        xuvDriver.updateStatus(DriverStatus.AVAILABLE);
        driverManager.addDriver(xuvDriver);

        Rider rider = new Rider("Sarthak", "9999999999");
        riderManager.addRider(rider);

        Location pickup = new Location(0, 0);
        Location drop = new Location(6, 8);

        System.out.println("=== Trip 1: GO ride ===");
        Trip goTrip = tripManager.createTrip(rider, pickup, drop, VehicleType.GO);
        System.out.println("Assigned driver: " + goTrip.getDriver().getId());
        System.out.println("Vehicle type: " + goTrip.getVehicleType());
        tripManager.startTrip(goTrip.getId());
        double goFare = tripManager.endTrip(goTrip.getId());
        System.out.printf("Fare (GO, %.1f km): ₹%.2f%n", pickup.distance(drop), goFare);

        System.out.println("\n=== Trip 2: SEDAN ride ===");
        Trip sedanTrip = tripManager.createTrip(rider, pickup, drop, VehicleType.SEDAN);
        System.out.println("Assigned driver: " + sedanTrip.getDriver().getId());
        System.out.println("Vehicle type: " + sedanTrip.getVehicleType());
        tripManager.startTrip(sedanTrip.getId());
        double sedanFare = tripManager.endTrip(sedanTrip.getId());
        System.out.printf("Fare (SEDAN, %.1f km): ₹%.2f%n", pickup.distance(drop), sedanFare);

        System.out.println("\n=== Trip 3: XUV ride with 1.5x surge ===");
        tripManager.setPricingStrategy(new SurgePricingStrategy(new DistanceBasedPricingStrategy(), 1.5));
        Trip xuvTrip = tripManager.createTrip(rider, pickup, drop, VehicleType.XUV);
        System.out.println("Assigned driver: " + xuvTrip.getDriver().getId());
        System.out.println("Vehicle type: " + xuvTrip.getVehicleType());
        tripManager.startTrip(xuvTrip.getId());
        double xuvFare = tripManager.endTrip(xuvTrip.getId());
        System.out.printf("Fare (XUV + surge, %.1f km): ₹%.2f%n", pickup.distance(drop), xuvFare);

        System.out.println("\n=== Trip 4: Request XUV when none available → Exception ===");
        try {
            tripManager.createTrip(rider, pickup, drop, VehicleType.XUV);
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
