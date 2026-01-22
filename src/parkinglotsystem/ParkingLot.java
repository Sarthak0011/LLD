package parkinglotsystem;

import parkinglotsystem.parkingspot.IParkingSpot;
import parkinglotsystem.vehicle.IVehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<ParkingFloor> parkingFloors = new ArrayList<>();

    public void addParkingFloor(ParkingFloor parkingFloor) {
        parkingFloors.add(parkingFloor);
    }

    public int getNextFloor() {
        return parkingFloors.size();
    }

    public void addParkingSpot(int floorNumber, IParkingSpot parkingSpot) {
        if(floorNumber >= parkingFloors.size()) {
            System.out.println(floorNumber + " is not available");
            return;
        }
        parkingFloors.get(floorNumber).addParkingSpot(parkingSpot);
    }

    public IParkingSpot getNearestSpot(IVehicle vehicle) {
        for(ParkingFloor parkingFloor: parkingFloors) {
            IParkingSpot parkingSpot = parkingFloor.getAvailableSpot(vehicle);
            if(parkingSpot != null) {
                return parkingSpot;
            }
        }
        return null;
    }

}
