package parkinglotsystem.parkingspot;

import parkinglotsystem.vehicle.IVehicle;
import parkinglotsystem.vehicle.VehicleSize;

public class MediumParkingSpot extends AbstractParkingSpot {

    public MediumParkingSpot(int spotNumber) {
        super(spotNumber);
    }

    @Override
    public boolean canFit(IVehicle vehicle) {
        return vehicle.getSize() == VehicleSize.MEDIUM;
    }
}


