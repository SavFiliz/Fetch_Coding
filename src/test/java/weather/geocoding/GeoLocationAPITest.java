package weather.geocoding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GeoLocationResponseUtils;
import weather.geocoding.api.GeoLocationAPI;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GeoLocationAPITest {

    private static final Logger logger = LoggerFactory.getLogger(GeoLocationAPITest.class);
    private final GeoLocationAPI api = new GeoLocationAPI();

    @Test
    public void testGetLocationByCityState() throws IOException {
        String cityState = "London, GB";
        logger.info("Starting test for getting location by City and State: {}", cityState);
        String result = api.getLocationByCityState(cityState);
        logger.debug("API response: {}", result);
        assertNotNull(result, "The result should not be null");
        GeoLocationResponseUtils.validateApiResponse(result, cityState);
        logger.info("Test passed: location successfully retrieved for {}", cityState);
    }

    @Test
    public void testGetLocationByZip() throws IOException {
        String zipCode = "90210";
        logger.info("Starting test for getting location by Zip code: {}", zipCode);
        String result = api.getLocationByZip(zipCode);
        logger.debug("API response for zip code {}: {}", zipCode, result);
        assertNotNull(result, "The result should not be null");
        GeoLocationResponseUtils.validateApiResponse(result, zipCode);
        logger.info("Test passed: location successfully retrieved for zip code {}", zipCode);
    }

    @ParameterizedTest
    @ValueSource(strings = {"lll, GB", "Paris, FR", "New York, US"})
    public void testGetLocationByCityStateParameterized(String cityState) throws IOException {
        logger.info("Starting test for city/state: {}", cityState);
        String result = api.getLocationByCityState(cityState);
        logger.debug("API response for {}: {}", cityState, result);
        assertNotNull(result, "The result should not be null");
        GeoLocationResponseUtils.validateApiResponse(result, cityState);
        logger.info("Test passed for city/state: {}", cityState);
    }

    @Test
    public void testInvalidCityState() throws IOException {
        String cityState = "lll, GB";  // Invalid city/state
        logger.info("Starting test for invalid city/state: {}", cityState);
        String result = api.getLocationByCityState(cityState);
        logger.debug("API response: {}", result);
        assertNotNull(result, "The result should not be null");
        try {
            // Validate the response
            GeoLocationResponseUtils.validateApiResponse(result, cityState);
            fail("Expected failure, but the API returned valid data for an invalid city/state: " + cityState);
        } catch (IllegalArgumentException e) {
            // This is the expected behavior for an invalid city/state
            logger.info("Expected validation failure for invalid city/state: {}", cityState);
        }
    }
}