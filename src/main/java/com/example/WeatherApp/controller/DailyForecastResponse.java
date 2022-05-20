package com.example.WeatherApp.controller;

import java.time.LocalDate;
import java.util.List;

public class DailyForecastResponse {
    private final float temperature;
    private final LocalDate date;
    private final float windSpeed;
    private final float maxTemperature;
    private final float minTemperature;
    private final List<WeatherResponse> weatherResponse;

    public DailyForecastResponse(
            float temperature,
            LocalDate date,
            float windSpeed,
            float maxTemperature,
            float minTemperature,
            List<WeatherResponse> weatherResponse
    ) {
        this.temperature = temperature;
        this.date = date;
        this.windSpeed = windSpeed;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.weatherResponse = weatherResponse;
    }

    public float getTemperature() {
        return temperature;
    }

    public LocalDate getDate() {
        return date;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public List<WeatherResponse> getWeatherResponse() {
        return weatherResponse;
    }

    public static class WeatherResponse {
        private final String cityName;

        public WeatherResponse(String cityName) {
            this.cityName = cityName;
        }

        public String getCityName() {
            return cityName;
        }
    }
}
