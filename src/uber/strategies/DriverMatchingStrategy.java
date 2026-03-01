package uber.strategies;

import uber.entities.Driver;
import uber.entities.TripMetaData;

public interface DriverMatchingStrategy {
    Driver matchDriver(TripMetaData tripMetaData);
}
