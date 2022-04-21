package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.City;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherBitClient {
    private RestTemplate restTemplate = new RestTemplate();
    private static final String WEATHER_URL = "http://api.weatherbit.io/v2.0/forecast/daily?";
    private static final String APP_KEY = "5a3dfd7b3d8f4afc8e7af78e824064f4";

    public String getWeather(City city) {
        return restTemplate.getForObject(WEATHER_URL + "lat={lat}&lon={lon}&key={key}", String.class,
                city.getLat(), city.getLon(), APP_KEY);
    }

}
