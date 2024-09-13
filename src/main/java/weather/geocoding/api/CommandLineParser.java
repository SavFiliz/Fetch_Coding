package weather.geocoding.api;

public class CommandLineParser {
    public static void main(String[] args) {
        GeoLocationAPI api = new GeoLocationAPI();

        for (String location : args) {
            try {
                if (location.matches("\\d{5}")) {
                    // Zip code
                    String result = api.getLocationByZip(location);
                    System.out.println("Zip code result: " + result);
                } else {
                    // City, State
                    String result = api.getLocationByCityState(location);
                    System.out.println("City/State result: " + result);
                }
            } catch (Exception e) {
                System.out.println("Error retrieving location for " + location + ": " + e.getMessage());
            }
        }
    }
}
