package uber.entities;

import uber.enums.VehicleType;

import java.util.*;


public class Vehicle {
    private final String id;
    private final String licenseNumber;
    private final VehicleType type;

    public Vehicle(String licenseNumber, VehicleType type) {
        this.id = UUID.randomUUID().toString();
        this.licenseNumber = licenseNumber;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public VehicleType getType() {
        return type;
    }
}