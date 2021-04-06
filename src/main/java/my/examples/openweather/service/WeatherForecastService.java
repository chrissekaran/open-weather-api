package my.examples.openweather.service;

import lombok.RequiredArgsConstructor;
import my.examples.openweather.client.WeatherClient;
import my.examples.openweather.dto.WeatherForecastResponse;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherForecastService {

    private final WeatherClient weatherClient;

    public WeatherForecastResponse fetchLocationWeatherFromOpenWeather(String locationId) {
        return weatherClient.fetchFromOpenWeather(locationId);
    }

    public Collection<WeatherForecastResponse> fetchFavouriteLocationsWeatherFromOpenWeather(String unit, Collection<String> locationIds) {
        return locationIds.stream()
                .map(weatherClient::fetchFromOpenWeather)
                .collect(Collectors.toList());
    }
}
