package com.example.WeatherApp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyWeatherForecastDto {
    public final float temperature;
    public final LocalDate date;
    public final float windSpeed;
    public final float maxTemperature;
    public final float minTemperature;

    public DailyWeatherForecastDto(
            @JsonProperty("temp") float temperature,
            @JsonProperty("datetime") LocalDate date,
            @JsonProperty("wind_spd") float windSpeed,
            @JsonProperty("max_temp") float maxTemperature,
            @JsonProperty("min_temp") float minTemperature) {
        this.temperature = temperature;
        this.date = date;
        this.windSpeed = windSpeed;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyWeatherForecastDto that = (DailyWeatherForecastDto) o;
        return Float.compare(that.temperature, temperature) == 0 && Float.compare(that.windSpeed, windSpeed) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, date, windSpeed);
    }

    @Override
    public String toString() {
        return "DailyWeatherForecastDto{" +
                "temperature=" + temperature +
                ", date=" + date +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
