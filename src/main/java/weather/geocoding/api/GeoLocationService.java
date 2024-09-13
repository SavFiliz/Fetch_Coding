package weather.geocoding.api;

import java.io.IOException;

public interface GeoLocationService {
    String getLocationByCityState(String cityState) throws IOException;
    String getLocationByZip(String zipCode) throws IOException;
}
