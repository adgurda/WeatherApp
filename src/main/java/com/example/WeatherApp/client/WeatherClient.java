package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.dto.WeatherDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherClient {

    WeatherDto getWeather(City city) throws JsonProcessingException;
}
