package uber.entities;

import uber.enums.TripStatus;
import uber.enums.VehicleType;

import java.util.UUID;

public class Trip {
    private Rider rider;
    private Driver driver;
    private final String id;
    private Location src;
    private Location dest;
    private TripStatus status;
    private final VehicleType vehicleType;

    public Trip(Rider rider, Driver driver, Location src, Location dest, TripStatus status, VehicleType vehicleType) {
        this.id = UUID.randomUUID().toString();
        this.rider = rider;
        this.driver = driver;
        this.src = src;
        this.dest = dest;
        this.status = status;
        this.vehicleType = vehicleType;
    }

    public void updateStatus(TripStatus newStatus) {
        this.status = newStatus;
    }

    public String getId() {
        return id;
    }

    public TripStatus getStatus() {
        return status;
    }

    public Rider getRider() {
        return rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public Location getSrc() {
        return src;
    }

    public Location getDest() {
        return dest;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
