package com.example.WeatherApp.controller;

import java.time.LocalDate;
import java.util.List;

public class WeatherResponse {
    public final String cityName;
    public final List<DailyWeatherResponse> dailyForecastResponse;

    public WeatherResponse(String cityName, List<DailyWeatherResponse> dailyForecastResponse) {
        this.cityName = cityName;
        this.dailyForecastResponse = dailyForecastResponse;
    }

    public String getCityName() {
        return cityName;
    }


    public List<DailyWeatherResponse> getDailyForecastResponse() {
        return dailyForecastResponse;
    }

    public static class DailyWeatherResponse{
        public final float temperature;
        public final LocalDate date;
        public final float windSpeed;
        public final float maxTemperature;
        public final float minTemperature;

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
