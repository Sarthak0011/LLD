package uber.entities;

import uber.enums.DriverStatus;

public class Driver extends User {
    private DriverStatus driverStatus;
    private Location currentLocation;
    private Vehicle vehicle;

    public Driver(String name, String contact) {
        super(name, contact);
        this.driverStatus = DriverStatus.OFFLINE;
    }

    public void updateStatus(DriverStatus status) {
        this.driverStatus = status;
    }

    public void updateLocation(Location location) {
        this.currentLocation = location;
    }

    public void registerVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}