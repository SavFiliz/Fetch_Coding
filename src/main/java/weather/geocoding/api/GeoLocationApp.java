package weather.geocoding.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;

public class GeoLocationApp {
    public static void main(String[] args) {
        GeoLocationAPI geoLocationAPI = new GeoLocationAPI();

        try {
            // Example: Get location by city and state
            String cityStateResponse = geoLocationAPI.getLocationByCityState("London, GB");
            System.out.println("City and State Response: " + cityStateResponse);

            // Example: Get location by zip code
            String zipResponse = geoLocationAPI.getLocationByZip("90210");
            System.out.println("Zip Code Response: " + zipResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }

    try {
        // Making the API request
        String response = geoLocationAPI.getLocationByCityState("London, GB");

        if (response.isEmpty()) {
            System.out.println("No response received");
        } else {
            System.out.println("Response: " + response);

            // Parsing the response using Gson
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(response, JsonArray.class);

            if (jsonArray.size() > 0) {
                JsonObject location = jsonArray.get(0).getAsJsonObject();  // Get the first result
                double lat = location.get("lat").getAsDouble();
                double lon = location.get("lon").getAsDouble();
                System.out.println("Latitude: " + lat + ", Longitude: " + lon);
            } else {
                System.out.println("No location data found in the response.");
            }
        }

    } catch (IOException e) {
        // Handle any IOException that might occur during the API call
        System.err.println("Error occurred: " + e.getMessage());
    } catch (Exception e) {
        // Handle any other exceptions, such as parsing issues
        System.err.println("An unexpected error occurred: " + e.getMessage());
    }
}
}