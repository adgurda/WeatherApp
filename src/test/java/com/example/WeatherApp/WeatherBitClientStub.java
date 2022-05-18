package com.example.WeatherApp;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.client.WeatherClient;
import com.example.WeatherApp.dto.DailyWeatherForecastDto;
import com.example.WeatherApp.dto.WeatherForecastDto;
import com.example.WeatherApp.exception.MappingException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static com.example.WeatherApp.cities.City.JASTARNIA;

public class WeatherBitClientStub implements WeatherClient {

    private static Map<City, WeatherForecastDto> weatherResponses  = Map.of(JASTARNIA, null);

    @Override
    public WeatherForecastDto getWeather(City city) {
        return weatherResponses.get(city);
    }

    @Override
    public WeatherForecastDto getDailyForecast(City city, LocalDate date) throws JsonProcessingException, MappingException {
        return null;
    }
}
