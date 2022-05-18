package com.example.WeatherApp.controller;

import com.example.WeatherApp.dto.DailyWeatherForecastDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class WeatherResponse {
    private final String cityName;
    private final List<DailyWeatherForecastDto> weatherForecastDtos;

    public WeatherResponse(String cityName, List<DailyWeatherForecastDto> weatherForecastDtos) {
        this.cityName = cityName;
        this.weatherForecastDtos = weatherForecastDtos;
    }

    public String getCityName() {
        return cityName;
    }

    public List<DailyWeatherForecastDto> getWeatherForecastDtos() {
        return weatherForecastDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherResponse that = (WeatherResponse) o;
        return Objects.equals(cityName, that.cityName) && Objects.equals(weatherForecastDtos, that.weatherForecastDtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, weatherForecastDtos);
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "cityName='" + cityName + '\'' +
                ", weatherForecastDtos=" + weatherForecastDtos +
                '}';
    }
}
