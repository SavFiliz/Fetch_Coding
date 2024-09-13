package utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GeoLocationResponseValidator {

    private static final Gson gson = new Gson();

    // Method to validate API response for city/state or zip
    public static void validateResponse(String response, String location) throws IllegalArgumentException {
        if (response.isEmpty()) {
            throw new IllegalArgumentException("No response received for location: " + location);
        }

        JsonArray jsonArray = gson.fromJson(response, JsonArray.class);

        if (jsonArray.size() == 0) {
            throw new IllegalArgumentException("No location data found in response for location: " + location);
        }

        JsonObject locationData = jsonArray.get(0).getAsJsonObject();
        if (!locationData.has("lat") || !locationData.has("lon")) {
            throw new IllegalArgumentException("Invalid data: Latitude or Longitude missing for location: " + location);
        }

        // Optionally, you could log the valid data
        double lat = locationData.get("lat").getAsDouble();
        double lon = locationData.get("lon").getAsDouble();
        System.out.println("Valid location: " + location + " | Latitude: " + lat + ", Longitude: " + lon);
    }
}
