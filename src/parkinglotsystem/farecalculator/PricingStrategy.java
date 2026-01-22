package parkinglotsystem.farecalculator;

import parkinglotsystem.Ticket;
import parkinglotsystem.vehicle.IVehicle;

public interface PricingStrategy {
    double calculateFare(Ticket ticket);
}
