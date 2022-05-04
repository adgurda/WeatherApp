package com.example.WeatherApp.dto;
@Builder
public class WeatherDto {
    private String name;
    private float temperature;
    private float windSpeed;

    public String getName() {
        return name;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getWindSpeed() {
        return windSpeed;
    }
}
