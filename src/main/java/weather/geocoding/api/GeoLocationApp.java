package weather.geocoding.api;

import utils.GeoLocationResponseValidator;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GeoLocationApp {

    public static void main(String[] args) {
        GeoLocationAPI geoLocationAPI = new GeoLocationAPI();
        List<String> locations = Arrays.asList("London, GB", "lll, GB", "90210");

        for (String location : locations) {
            try {
                String response;
                if (location.contains(",")) {
                    response = geoLocationAPI.getLocationByCityState(location);
                } else {
                    response = geoLocationAPI.getLocationByZip(location);
                }

                // Validate the API response using the utility
                GeoLocationResponseValidator.validateResponse(response, location);

            } catch (IOException e) {
                System.err.println("Network error while processing location " + location + ": " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("Validation error: " + e.getMessage());
            }
        }
    }
}
