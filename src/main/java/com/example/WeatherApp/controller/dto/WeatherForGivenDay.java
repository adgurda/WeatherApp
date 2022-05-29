package com.example.WeatherApp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForGivenDay {
    public final String cityName;
    public final float temperature;

    public final LocalDate date;
    public final float windSpeed;
    public final float maxTemperature;
    public final float minTemperature;

    public WeatherForGivenDay(
                              @JsonProperty("city_name") String cityName,
                              @JsonProperty("temp") float temperature,
                              @JsonProperty("datetime") LocalDate date,
                              @JsonProperty("wind_spd") float windSpeed,
                              @JsonProperty("max_temp") float maxTemperature,
                              @JsonProperty("min_temp") float minTemperature) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.date = date;
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
    public String toString() {
        return "WeatherForGivenDay{" +
                "cityName='" + cityName + '\'' +
                ", temperature=" + temperature +
                ", date=" + date +
                ", windSpeed=" + windSpeed +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForGivenDay that = (WeatherForGivenDay) o;
        return Float.compare(that.temperature, temperature) == 0 && Float.compare(that.windSpeed, windSpeed) == 0 && Float.compare(that.maxTemperature, maxTemperature) == 0 && Float.compare(that.minTemperature, minTemperature) == 0 && Objects.equals(cityName, that.cityName) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, temperature, date, windSpeed, maxTemperature, minTemperature);
    }
}
