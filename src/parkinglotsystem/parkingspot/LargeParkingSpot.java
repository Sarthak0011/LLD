package parkinglotsystem.parkingspot;

import parkinglotsystem.vehicle.IVehicle;
import parkinglotsystem.vehicle.VehicleSize;

public class LargeParkingSpot extends AbstractParkingSpot {

    public LargeParkingSpot(int spotNumber) {
        super(spotNumber);
    }

    @Override
    public boolean canFit(IVehicle vehicle) {
        return vehicle.getSize() == VehicleSize.LARGE;
    }
}
