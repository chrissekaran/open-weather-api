package my.examples.openweather.client;

import lombok.RequiredArgsConstructor;
import my.examples.openweather.dto.WeatherForecastResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class WeatherClient {

    private static final String API_KEY = "";
    private static final String CITY_ID_PLACEHOLDER = "CITY_ID_PLACEHOLDER";

    private final RestTemplate restTemplate;

    public WeatherForecastResponse fetchFromOpenWeather(String locationId) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("api.openweathermap.org")
                .path("/data/2.5/forecast")
                .queryParam("q", "London,uk")
                .queryParam("APPID", API_KEY)
                .buildAndExpand(locationId);

        ResponseEntity<WeatherForecastResponse> response = restTemplate.getForEntity(uriComponents.toUriString(), WeatherForecastResponse.class);
        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Something went wrong with the response. " );
        }
    }

}
