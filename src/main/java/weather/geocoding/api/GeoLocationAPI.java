package weather.geocoding.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class GeoLocationAPI implements GeoLocationService{

    private static final String API_KEY = "f897a99d971b5eef57be6fafa0d83239";
    private static final String BASE_URL = "http://api.openweathermap.org/geo/1.0/";
    private OkHttpClient client = new OkHttpClient();

    // Method to get location by city, state, and country name
    public String getLocationByCityState(String cityState) throws IOException {
        // Replace spaces with %20 for URL encoding
        cityState = cityState.replace(" ", "%20");

        String url = BASE_URL + "direct?q=" + cityState + "&limit=5&appid=" + API_KEY;
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // Method to get location by zip code
    public String getLocationByZip(String zipCode) throws IOException {
        String url = BASE_URL + "zip?zip=" + zipCode + ",us&appid=" + API_KEY;
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}