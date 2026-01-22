package parkinglotsystem.vehicle;

public class Car implements IVehicle {

    private final String licenseNumber;

    public Car(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }

    @Override
    public String getLicenseNumber() {
        return this.licenseNumber;
    }
}
