package com.example.WeatherApp.controller;

import java.time.LocalDate;
import java.util.List;

public class WeatherResponse {
    private final String cityName;
    private final List<DailyForecastResponse> dailyForecastResponse;

    public WeatherResponse(String cityName, List<DailyForecastResponse> dailyForecastResponse) {
        this.cityName = cityName;
        this.dailyForecastResponse = dailyForecastResponse;
    }

    public String getCityName() {
        return cityName;
    }

    public List<DailyForecastResponse> getDailyForecastResponse() {
        return dailyForecastResponse;
    }

    public static class DailyWeatherResponse{
        private final float temperature;
        private final LocalDate date;
        private final float windSpeed;
        private final float maxTemperature;
        private final float minTemperature;

        public DailyWeatherResponse(
                float temperature,
                LocalDate date,
                float windSpeed,
                float maxTemperature,
                float minTemperature) {
            this.temperature = temperature;
            this.date = date;
            this.windSpeed = windSpeed;
            this.maxTemperature = maxTemperature;
            this.minTemperature = minTemperature;
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
    }
}
