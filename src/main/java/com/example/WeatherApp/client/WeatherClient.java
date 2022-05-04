package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.City;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherClient {

    String getWeather(City city) throws JsonProcessingException;
}
