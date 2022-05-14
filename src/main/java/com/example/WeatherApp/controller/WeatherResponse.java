package com.example.WeatherApp.controller;

import java.time.LocalDate;
import java.util.Objects;

public class WeatherResponse {
    private final float temperature;
    private final LocalDate date;
    private final float windSpeed;
    private final float maxTemperature;
    private final float minTemperature;

    public WeatherResponse(float temperature, LocalDate date, float windSpeed, float maxTemperature, float minTemperature) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherResponse that = (WeatherResponse) o;
        return Float.compare(that.temperature, temperature) == 0 && Float.compare(that.windSpeed, windSpeed) == 0 && Float.compare(that.maxTemperature, maxTemperature) == 0 && Float.compare(that.minTemperature, minTemperature) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, date, windSpeed, maxTemperature, minTemperature);
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "temperature=" + temperature +
                ", date=" + date +
                ", windSpeed=" + windSpeed +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                '}';
    }
}
