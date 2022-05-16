package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.controller.WeatherResponse;
import com.example.WeatherApp.dto.WeatherForecastDto;
import com.example.WeatherApp.exception.MappingException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;

public interface WeatherClient {

    WeatherForecastDto getWeather(City city) throws JsonProcessingException, MappingException;
    WeatherResponse getForecastByDate(City city, LocalDate date) throws JsonProcessingException;
}
