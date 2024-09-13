package weather.geocoding.api;

public record LocationResponse(
        String name,
        double lat,
        double lon,
        String state,
        String country
) {
}
