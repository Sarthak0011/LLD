package parkinglotsystem;

import parkinglotsystem.parkingspot.IParkingSpot;
import parkinglotsystem.vehicle.IVehicle;

import java.time.LocalDateTime;

public class Ticket {
    private final String ticketId;
    private final IParkingSpot parkingSpot;
    private final IVehicle vehicle;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(String ticketId, IVehicle vehicle, IParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
    }

    public void markExit() {
        this.exitTime = LocalDateTime.now();
    }

    public IVehicle getVehicle() {
        return vehicle;
    }

    public String getTicketId() {
        return ticketId;
    }

    public IParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }
}
