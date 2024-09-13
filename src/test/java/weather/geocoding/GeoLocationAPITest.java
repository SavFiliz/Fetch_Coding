package weather.geocoding;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import weather.geocoding.api.GeoLocationAPI;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeoLocationAPITest {
    private static final Logger logger = LoggerFactory.getLogger(GeoLocationAPITest.class);

    @Test
    public void testGetLocationByCityState() throws IOException {
        logger.info("Starting test for getting location by City and State");
        GeoLocationAPI api = new GeoLocationAPI();
        String result = api.getLocationByCityState("London, GB");
        logger.debug("API response: {}", result);
        assertNotNull(result, "The result should not be null");
        logger.info("Test passed: location successfully retrieved for London, GB");
    }

    @Test
    public void testGetLocationByZip() throws IOException {
        logger.info("Starting test for getting location by Zip code");
        GeoLocationAPI api = new GeoLocationAPI();
        String result = api.getLocationByZip("90210");
        logger.debug("API response: {}", result);
        assertNotNull(result, "The result should not be null");
        logger.info("Test passed: location successfully retrieved for zip code 90210");
    }

    @ParameterizedTest
    @ValueSource(strings = {"London, GB", "Paris, FR", "New York, US"})
    public void testGetLocationByCityState(String cityState) throws IOException {
        logger.info("Starting test for city/state: {}", cityState);
        GeoLocationAPI api = new GeoLocationAPI();
        String result = api.getLocationByCityState(cityState);
        logger.debug("API response for {}: {}", cityState, result);
        assertNotNull(result, "The result should not be null");
        logger.info("Test passed for city/state: {}", cityState);
    }
}
