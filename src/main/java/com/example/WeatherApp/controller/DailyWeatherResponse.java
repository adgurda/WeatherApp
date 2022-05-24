package com.example.WeatherApp.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyWeatherResponse {
    private final String cityName;
    private final float temperature;
    private final float windSpeed;
    private final float maxTemperature;
    private final float minTemperature;

    @JsonCreator
    public DailyWeatherResponse(@JsonProperty("cityName") String cityName,
                                @JsonProperty("temperature") float temperature,
                                @JsonProperty("windSpeed") float windSpeed,
                                @JsonProperty("maxTemperature") float maxTemperature,
                                @JsonProperty("minTemperature") float minTemperature) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public String getCityName() {
        return cityName;
    }

    public float getTemperature() {
        return temperature;
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

    @Override
    public String toString() {
        return "DailyWeatherResponse{" +
                "cityName='" + cityName + '\'' +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                '}';
    }
}
