package parkinglotsystem.parkingspot;

import parkinglotsystem.vehicle.IVehicle;
import parkinglotsystem.vehicle.VehicleSize;

public class SmallParkingSpot extends AbstractParkingSpot {

    public SmallParkingSpot(int spotNumber) {
        super(spotNumber);
    }

    @Override
    public boolean canFit(IVehicle vehicle) {
        return vehicle.getSize() == VehicleSize.SMALL;
    }
}
