package parkinglotsystem.vehicle;

public class Bike implements IVehicle{

    private final String licenseNumber;
    public Bike(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }

    @Override
    public String getLicenseNumber() {
        return this.licenseNumber;
    }
}
