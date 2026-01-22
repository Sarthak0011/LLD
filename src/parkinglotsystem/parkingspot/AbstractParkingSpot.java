package parkinglotsystem.parkingspot;

import parkinglotsystem.vehicle.IVehicle;

public abstract class AbstractParkingSpot implements IParkingSpot {

    private final int spotNumber;
    private int floorNumber;
    protected boolean isAvailable = true;
    protected IVehicle vehicle;

    protected AbstractParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setFloor(int floor) {
        this.floorNumber = floor;
    }

    @Override
    public int getFloor() {
        return floorNumber+1;
    }

    @Override
    public void occupy(IVehicle vehicle) {
        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    @Override
    public void vacate() {
        this.vehicle = null;
        this.isAvailable = true;
    }

    @Override
    public int getSpotNumber() {
        return spotNumber;
    }

}
