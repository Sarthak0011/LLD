package parkinglotsystem;

import parkinglotsystem.farecalculator.PricingStrategy;
import parkinglotsystem.parkingspot.IParkingSpot;
import parkinglotsystem.vehicle.IVehicle;

import java.util.UUID;

public class ParkingLotManager {

    private final ParkingLot parkingLot;
    private final PricingStrategy pricingStrategy;

    public ParkingLotManager(ParkingLot parkingLot, PricingStrategy pricingStrategy) {
        this.parkingLot = parkingLot;
        this.pricingStrategy = pricingStrategy;
    }

    public void addFloor() {
        parkingLot.addParkingFloor(new ParkingFloor(parkingLot.getNextFloor()));
    }

    public void addParkingSpot(int floor, IParkingSpot parkingSpot) {
        parkingLot.addParkingSpot(floor, parkingSpot);
    }

    public Ticket parkVehicle(IVehicle vehicle) {
        IParkingSpot parkingSpot = parkingLot.getNearestSpot(vehicle);
        if(parkingSpot == null) {
            System.out.println("No parking spot available");
            throw new RuntimeException("No Parking spot available");
        }
        parkingSpot.occupy(vehicle);
        System.out.println(vehicle.getLicenseNumber() + " parked" );
        System.out.println("SIZE: " + vehicle.getSize());
        System.out.println("FLOOR: " + parkingSpot.getFloor());
        System.out.println("SPOT: " + parkingSpot.getSpotNumber());
        System.out.println("==========================");
        return new Ticket(UUID.randomUUID().toString(), vehicle, parkingSpot);
    }

    public double unparkVehicle(Ticket ticket) {
        ticket.markExit();
        ticket.getParkingSpot().vacate();
        double totalFare = pricingStrategy.calculateFare(ticket);
        System.out.println("Unparked vehicle");
        System.out.println("TICKET ID: " + ticket.getTicketId());
        System.out.println("LICENSE NO: " + ticket.getVehicle().getLicenseNumber());
        System.out.println("TOTAL FARE: " + totalFare);
        System.out.println("==========================");
        return totalFare;
    }


}
