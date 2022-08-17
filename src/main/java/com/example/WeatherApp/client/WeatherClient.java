package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.controller.dto.WeatherForecastDto;
import com.example.WeatherApp.exception.MappingException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

public interface WeatherClient {

    WeatherForecastDto getWeather(City city) throws JsonProcessingException, MappingException, ExecutionException;
    WeatherForecastDto getDailyForecast (City city, LocalDate date) throws JsonProcessingException, MappingException;

}
