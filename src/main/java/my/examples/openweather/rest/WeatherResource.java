package my.examples.openweather.rest;

import lombok.RequiredArgsConstructor;
import my.examples.openweather.dto.WeatherForecastResponse;
import my.examples.openweather.client.WeatherClient;
import my.examples.openweather.service.WeatherForecastService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherResource {

    private final WeatherForecastService weatherForecastService;

    @GetMapping("/summary")
    public Collection<WeatherForecastResponse> summaryOfUsersFavouriteLocations(@RequestParam("unit") String unit, @RequestParam("locations") List<String> locations) {
        return weatherForecastService.fetchFavouriteLocationsWeatherFromOpenWeather(unit, locations);
    }

    @GetMapping("/locations/{locationId}")
    public WeatherForecastResponse weatherByLocationId(@PathVariable("locationId") String locationId) {
        return weatherForecastService.fetchLocationWeatherFromOpenWeather(locationId);
    }

}