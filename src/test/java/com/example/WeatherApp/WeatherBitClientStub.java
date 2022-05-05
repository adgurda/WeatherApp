package com.example.WeatherApp;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.client.WeatherClient;
import com.example.WeatherApp.dto.WeatherDto;

import java.util.Map;

import static com.example.WeatherApp.cities.City.JASTARNIA;

public class WeatherBitClientStub implements WeatherClient {

    private static Map<City, String> weatherResponses  = Map.of(JASTARNIA, "");

    @Override
    public WeatherDto getWeather(City city) {
        return weatherResponses.get(city);
    }
}
