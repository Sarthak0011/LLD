package parkinglotsystem.parkingspot;

import parkinglotsystem.vehicle.IVehicle;

public interface IParkingSpot {
    boolean isAvailable();
    void setFloor(int floor);
    int getFloor();
    void occupy(IVehicle vehicle);
    void vacate();
    int getSpotNumber();
    boolean canFit(IVehicle vehicle);
}
