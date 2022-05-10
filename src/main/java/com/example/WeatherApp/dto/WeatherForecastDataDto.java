package com.example.WeatherApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastDataDto {
    public final float temperature;
    public final String date;
    public final float windSpeed;

    public WeatherForecastDataDto(
            @JsonProperty("temp") float temperature,
            @JsonProperty("datetime") String date,
            @JsonProperty("wind_spd") float windSpeed) {
        this.temperature = temperature;
        this.date = date;
        this.windSpeed = windSpeed;
    }

    public float getTemperature() {
        return temperature;
    }

    public String getDate() {
        return date;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecastDataDto that = (WeatherForecastDataDto) o;
        return Float.compare(that.temperature, temperature) == 0 && Float.compare(that.windSpeed, windSpeed) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, date, windSpeed);
    }

    @Override
    public String toString() {
        return "WeatherForecastDataDto{" +
                "temperature=" + temperature +
                ", date='" + date + '\'' +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
