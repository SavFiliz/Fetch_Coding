package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeoLocationResponseUtils {

    private static final Logger logger = LoggerFactory.getLogger(GeoLocationResponseUtils.class);
    private static final Gson gson = new Gson();

    // Method to validate API response for city/state or zip
    public static void validateApiResponse(String result, String location) {
        // Check if the result starts with [ to identify if it's an array or object
        if (result.trim().startsWith("[")) {
            // It's a JsonArray (for city/state)
            JsonArray jsonArray = gson.fromJson(result, JsonArray.class);

            if (jsonArray.size() == 0) {
                throw new IllegalArgumentException("No location data found for location: " + location);
            }

            JsonObject locationData = jsonArray.get(0).getAsJsonObject();
            validateLocationData(locationData, location);

        } else {
            // It's a JsonObject (for zip code)
            JsonObject locationData = gson.fromJson(result, JsonObject.class);
            validateLocationData(locationData, location);
        }
    }

    // Method to validate the latitude and longitude in the JsonObject
    private static void validateLocationData(JsonObject locationData, String location) {
        if (!locationData.has("lat") || !locationData.has("lon")) {
            throw new IllegalArgumentException("Response does not contain valid latitude or longitude data for location: " + location);
        }
        // Optionally log the latitude and longitude
        double lat = locationData.get("lat").getAsDouble();
        double lon = locationData.get("lon").getAsDouble();
        logger.info("Valid location: {} | Latitude: {}, Longitude: {}", location, lat, lon);
    }
}

