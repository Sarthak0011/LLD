package uber.managers;

import uber.entities.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverManager {
    private static volatile DriverManager instance;
    private final Map<String, Driver> drivers;

    private DriverManager() {
        this.drivers = new ConcurrentHashMap<>();
    }

    public static DriverManager getInstance() {
        if (instance == null) {
           synchronized (DriverManager.class) {
               if (instance == null) {
                   instance = new DriverManager();
               }
           }
        }
        return instance;
    }

    public Driver getDriver(String driverId) {
        return drivers.get(driverId);
    }

    public void addDriver(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    public List<Driver> getAvailableDrivers() {
        return new ArrayList<>(drivers.values());
    }
}
