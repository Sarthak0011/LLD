package parkinglotsystem.vehicle;

public class Truck implements IVehicle {

    private final String licenseNumber;

    public Truck(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }

    @Override
    public String getLicenseNumber() {
        return this.licenseNumber;
    }
}
