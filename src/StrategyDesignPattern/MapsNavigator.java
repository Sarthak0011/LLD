package StrategyDesignPattern;

interface RouteStrategy {
    void findRoute(String source, String destination);
}

interface VehicleStrategy {
    void applyVehicleRules();
}

class FastestRouteStrategy implements RouteStrategy {

    @Override
    public void findRoute(String source, String destination) {
        System.out.println("Finding fastest route from " + source + " to " + destination);
    }
}

class ShortestRouteStrategy implements RouteStrategy {

    @Override
    public void findRoute(String source, String destination) {
        System.out.println("Finding shortest route from " + source + " to " + destination);
    }
}

class AvoidTollStrategy implements RouteStrategy {

    @Override
    public void findRoute(String source, String destination) {
        System.out.println("Finding no toll route from " + source + " to " + destination);
    }
}

class TwoWheelerStrategy implements VehicleStrategy {

    @Override
    public void applyVehicleRules() {
        System.out.println("Finding best route for two wheeler...");
    }
}

class FourWheeler implements VehicleStrategy {

    @Override
    public void applyVehicleRules() {
        System.out.println("Finding best route for four wheeler...");
    }
}

class PublicTransport implements VehicleStrategy {

    @Override
    public void applyVehicleRules() {
        System.out.println("Finding best route for public transport...");
    }
}


class NavigationSystem {
    private final VehicleStrategy vehicleStrategy;
    private final RouteStrategy routeStrategy;

    NavigationSystem(VehicleStrategy vehicleStrategy, RouteStrategy routeStrategy) {
        this.vehicleStrategy = vehicleStrategy;
        this.routeStrategy = routeStrategy;
    }

    public void applyVehicleRules() {
        this.vehicleStrategy.applyVehicleRules();
    }

    public void findRoute(String source, String destination) {
        this.routeStrategy.findRoute(source, destination);
    }
}

public class MapsNavigator {
    public static void main(String[] args) {
        NavigationSystem navigationSystem = new NavigationSystem(
                new TwoWheelerStrategy(),
                new ShortestRouteStrategy()
        );

        navigationSystem.applyVehicleRules();
        navigationSystem.findRoute("Ahmedabad", "Rajkot");
    }
}
