package com.example.WeatherApp.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastDto {
    private final String cityName;

    public WeatherForecastDto(@JsonProperty("city_name") String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecastDto that = (WeatherForecastDto) o;
        return Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName);
    }

    @Override
    public String toString() {
        return "WeatherForecastDto{" +
                "cityName='" + cityName + '\'' +
                '}';
    }
}
