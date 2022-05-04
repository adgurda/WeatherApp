package com.example.WeatherApp;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.client.WeatherClient;

import java.util.Map;

import static com.example.WeatherApp.cities.City.JASTARNIA;

public class WeatherBitClientStub implements WeatherClient {

    private static Map<City, String> weatherResponses  = Map.of(JASTARNIA, "");

    @Override
    public String getWeather(City city) {
        return weatherResponses.get(city);
    }
}
