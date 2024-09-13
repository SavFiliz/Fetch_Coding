 Fetch SDET Take-Home Test
 Overview

This project interacts with the OpenWeather Geocoding API to fetch latitude, longitude, and other location information based on city/state or zip code inputs. It handles both valid and invalid inputs and includes integration tests for validation.

 Features
- Fetch location data by city/state or zip code.
- Handle multiple locations at once.
- Validate responses to ensure location data is returned.
- Handle invalid inputs gracefully, passing the test when the API fails to return valid data.

 Usage

 Run the Utility
1. Clone the repository:
   ```bash
   git clone https://github.com/SavFiliz/Fetch_Coding.git
   cd Fetch_Coding
   ```
 Running Tests
To run tests:
```bash
./gradlew test
```

The tests cover:
- **Valid inputs** like `"London, GB"` and `"90210"`.
- **Invalid inputs** like `"lll, GB"` where no location data is returned, ensuring the test passes when expected failures occur.

 Example API Responses

- **City/State**:  
  Input: `"London, GB"`  
  Output: `Latitude: 51.5073, Longitude: -0.1276`

- **Zip Code**:  
  Input: `"90210"`  
  Output: `Latitude: 34.0901, Longitude: -118.4065`

 Logging
The utility logs API requests, responses, and validation outcomes for easy debugging and tracking.

 API Key
The API key used is publicly available. If rate-limited, consider generating your own API key from [OpenWeather](https://home.openweathermap.org/users/sign_up).
