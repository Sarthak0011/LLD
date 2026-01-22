package parkinglotsystem.farecalculator;

import parkinglotsystem.Ticket;
import parkinglotsystem.parkingspot.IParkingSpot;
import parkinglotsystem.vehicle.IVehicle;

import java.time.Duration;

public class HourlyPricingStrategy implements PricingStrategy {

    @Override
    public double calculateFare(Ticket ticket) {
        long minutes = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toMinutes();
        return getRate(ticket.getVehicle()) * Math.max(1, Math.ceil(minutes / 60d));
    }

    private double getRate(IVehicle vehicle) {
        return switch (vehicle.getSize()) {
            case SMALL -> 10;
            case MEDIUM -> 20;
            case LARGE -> 30;
        };
    }
}
