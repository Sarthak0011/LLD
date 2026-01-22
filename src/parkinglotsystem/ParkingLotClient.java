package parkinglotsystem;

import parkinglotsystem.farecalculator.HourlyPricingStrategy;
import parkinglotsystem.farecalculator.PricingStrategy;
import parkinglotsystem.parkingspot.LargeParkingSpot;
import parkinglotsystem.parkingspot.MediumParkingSpot;
import parkinglotsystem.parkingspot.SmallParkingSpot;
import parkinglotsystem.vehicle.Bike;
import parkinglotsystem.vehicle.Car;
import parkinglotsystem.vehicle.IVehicle;
import parkinglotsystem.vehicle.Truck;

public class ParkingLotClient {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        PricingStrategy pricingStrategy = new HourlyPricingStrategy();
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLot, pricingStrategy);

        parkingLotManager.addFloor();
        parkingLotManager.addFloor();

        parkingLotManager.addParkingSpot(0, new MediumParkingSpot(1));
        parkingLotManager.addParkingSpot(0, new MediumParkingSpot(2));
        parkingLotManager.addParkingSpot(0, new LargeParkingSpot(3));
        parkingLotManager.addParkingSpot(0, new LargeParkingSpot(4));
        parkingLotManager.addParkingSpot(1, new SmallParkingSpot(1));
        parkingLotManager.addParkingSpot(1, new SmallParkingSpot(2));
        parkingLotManager.addParkingSpot(1, new SmallParkingSpot(3));

        IVehicle vehicle1 = new Bike("GJ 1234");
        IVehicle vehicle2 = new Car("GJ 5555");
        IVehicle vehicle3 = new Truck("GJ 9999");

        Ticket t1 = parkingLotManager.parkVehicle(vehicle1);
        Ticket t2 = parkingLotManager.parkVehicle(vehicle2);
        Ticket t3 = parkingLotManager.parkVehicle(vehicle3);

        parkingLotManager.unparkVehicle(t2);


    }
}
