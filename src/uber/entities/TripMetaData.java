package uber.entities;

import uber.enums.VehicleType;

public class TripMetaData {
    private final Driver driver;
    private final Rider rider;
    private final Location src;
    private final Location dest;
    private final VehicleType requestedVehicleType;

    public TripMetaData(Driver driver, Rider rider, Location src, Location dest, VehicleType requestedVehicleType) {
        this.driver = driver;
        this.rider = rider;
        this.src = src;
        this.dest = dest;
        this.requestedVehicleType = requestedVehicleType;
    }

    public Driver getDriver() {
        return driver;
    }

    public Rider getRider() {
        return rider;
    }

    public Location getSrc() {
        return src;
    }

    public Location getDest() {
        return dest;
    }

    public VehicleType getRequestedVehicleType() {
        return requestedVehicleType;
    }
}
