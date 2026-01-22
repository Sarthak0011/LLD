package parkinglotsystem;

import parkinglotsystem.parkingspot.IParkingSpot;
import parkinglotsystem.vehicle.IVehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private final int floorNumber;
    private final List<IParkingSpot> parkingSpots = new ArrayList<>();

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void addParkingSpot(IParkingSpot parkingSpot) {
        parkingSpot.setFloor(floorNumber);
        parkingSpots.add(parkingSpot);
    }

    public IParkingSpot getAvailableSpot(IVehicle vehicle) {
        for(IParkingSpot parkingSpot: parkingSpots) {
            if(parkingSpot.isAvailable() && parkingSpot.canFit(vehicle)) {
                return parkingSpot;
            }
        }
        return null;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

}
