# Parking Lot System: Model Relationships & UML Specification

This document provides a detailed specification of the relationships between classes in the Parking Lot System, following standard UML principles. It is designed to be used by an AI to generate a precise UML Class Diagram.

## 1. Class Definitions & Relationships

### Core Management
- **ParkingLotManager**
  - **Relationship**: 
    - *Composition* with `ParkingLot`: The manager owns and controls exactly one parking lot instance.
    - *Association (Direct)* with `PricingStrategy`: The manager utilizes a specific strategy for fare calculation.
    - *Dependency* on `Ticket`: Produces a `Ticket` upon parking and consumes one upon unparking.
    - *Dependency* on `IVehicle`: Takes a vehicle instance to initiate parking.

- **ParkingLot**
  - **Relationship**: 
    - *Composition (1:N)* with `ParkingFloor`: A parking lot consists of multiple floors. The floors do not exist independently of the lot.

- **ParkingFloor**
  - **Relationship**: 
    - *Composition (1:N)* with `IParkingSpot`: Each floor contains multiple parking spots.
    - *Dependency* on `IVehicle`: Evaluates if a vehicle can fit in available spots.

### Parking Spots (Inheritance Hierarchy)
- **IParkingSpot** (Interface)
  - Defines the contract for all parking spots.
- **AbstractParkingSpot** (Abstract Class)
  - **Relationship**: 
    - *Realization*: Implements `IParkingSpot`.
    - *Association (Optional)* with `IVehicle`: Holds a reference to the `IVehicle` currently occupying the spot (0..1 relationship).
- **SmallParkingSpot / MediumParkingSpot / LargeParkingSpot** (Concrete Classes)
  - **Relationship**: 
    - *Generalization*: Extends `AbstractParkingSpot`.

### Vehicles (Inheritance Hierarchy)
- **IVehicle** (Interface)
  - Defines the contract for all vehicles.
  - **Relationship**: 
    - *Association* with `VehicleSize`: Every vehicle has exactly one size.
- **Bike / Car / Truck** (Concrete Classes)
  - **Relationship**: 
    - *Realization*: Implements `IVehicle`.

### Support & Logic
- **Ticket** (Entity)
  - **Relationship**: 
    - *Association* with `IVehicle`: Each ticket is linked to exactly one vehicle.
    - *Association* with `IParkingSpot`: Each ticket is linked to exactly one parking spot.
  - **Note**: Acts as the "receipt" connecting the vehicle to its location and duration.

- **PricingStrategy** (Interface)
  - **Relationship**: 
    - *Dependency* on `Ticket`: Uses ticket data (entry/exit times) to calculate fares.
- **HourlyPricingStrategy** (Concrete Class)
  - **Relationship**: 
    - *Realization*: Implements `PricingStrategy`.

- **VehicleSize** (Enum)
  - Values: `SMALL`, `MEDIUM`, `LARGE`.

## 2. UML Standard Relationship Summary

| Source | Relationship Type | Target | Description |
| :--- | :--- | :--- | :--- |
| `ParkingLotManager` | Composition | `ParkingLot` | Manager owns the lot. |
| `ParkingLot` | Composition (1:*) | `ParkingFloor` | Lot contains multiple floors. |
| `ParkingFloor` | Composition (1:*) | `IParkingSpot` | Floor contains multiple spots. |
| `AbstractParkingSpot` | Realization | `IParkingSpot` | Implements interface. |
| `Small/Medium/LargeSpot` | Generalization | `AbstractParkingSpot` | Inherits base logic. |
| `AbstractParkingSpot` | Association (0..1) | `IVehicle` | Spot may hold a vehicle. |
| `Ticket` | Association (1:1) | `IVehicle` | Ticket belongs to a vehicle. |
| `Ticket` | Association (1:1) | `IParkingSpot` | Ticket points to a spot. |
| `Bike/Car/Truck` | Realization | `IVehicle` | Implements interface. |
| `IVehicle` | Association | `VehicleSize` | Defines vehicle capacity needs. |
| `HourlyPricingStrategy`| Realization | `PricingStrategy` | Concrete implementation. |
| `ParkingLotManager` | Association | `PricingStrategy` | Strategy used for billing. |

## 3. Workflow Logic (Interactions)
1. `ParkingLotManager` receives `IVehicle`.
2. `ParkingLot` finds `IParkingSpot` via `ParkingFloor`.
3. `IParkingSpot` state changes to occupied + stores `IVehicle`.
4. `Ticket` is created with references to both.
5. `PricingStrategy` calculates cost using `Ticket` timestamps during unparking.
