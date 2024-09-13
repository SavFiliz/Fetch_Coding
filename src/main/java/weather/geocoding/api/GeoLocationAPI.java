package weather.geocoding.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GeoLocationAPI implements GeoLocationService {

    private static final String API_KEY = "f897a99d971b5eef57be6fafa0d83239";
    private static final String BASE_URL = "http://api.openweathermap.org/geo/1.0/";
    private final OkHttpClient client = new OkHttpClient();

    // Method to get location by city, state, and country name
    public String getLocationByCityState(String cityState) throws IOException {
        // Proper URL encoding for cityState
        String encodedCityState = URLEncoder.encode(cityState, StandardCharsets.UTF_8.toString());

        String url = BASE_URL + "direct?q=" + encodedCityState + "&limit=5&appid=" + API_KEY;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    // Method to get location by zip code
    public String getLocationByZip(String zipCode) throws IOException {
        // Proper URL encoding for zipCode if needed
        String url = BASE_URL + "zip?zip=" + URLEncoder.encode(zipCode, StandardCharsets.UTF_8.toString()) + ",us&appid=" + API_KEY;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}
