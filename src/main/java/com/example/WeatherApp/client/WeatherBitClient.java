package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.City;
import org.springframework.web.client.RestTemplate;

public class WeatherBitClient implements WeatherClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String host;
    private final String apiKey;

    public WeatherBitClient(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
    }

    @Override
    public String getWeather(City city) {
        return restTemplate.getForObject(host + "/v2.0/forecast/daily?lat={lat}&lon={lon}&key={key}", String.class,
                city.getLat(), city.getLon(), apiKey);
    }

}
