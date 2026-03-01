package uber.managers;

import uber.entities.Rider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RiderManager {
    private static volatile RiderManager instance;
    private final Map<String, Rider> riders;

    private RiderManager() {
        this.riders = new ConcurrentHashMap<>();
    }

    public static RiderManager getInstance() {
        if (instance == null) {
            synchronized (RiderManager.class) {
                if (instance == null) {
                    instance = new RiderManager();
                }
            }
        }
        return instance;
    }

    public Rider getRider(String riderId) {
        return riders.get(riderId);
    }

    public void addRider(Rider rider) {
        riders.put(rider.getId(), rider);
    }
}
