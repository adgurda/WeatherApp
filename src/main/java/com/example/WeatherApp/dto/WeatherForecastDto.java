package com.example.WeatherApp.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastDto {
    private final String cityName;
    private final List<WeatherForecastDataDto> weatherForecastDataDto;

    public WeatherForecastDto(@JsonProperty("city_name") String cityName,
                              @JsonProperty("data")List<WeatherForecastDataDto> weatherForecastDataDto) {
        this.cityName = cityName;
        this.weatherForecastDataDto = weatherForecastDataDto;
    }

    public String getCityName() {
        return cityName;
    }

    public List<WeatherForecastDataDto> getWeatherForecastDataDto() {
        return weatherForecastDataDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecastDto that = (WeatherForecastDto) o;
        return Objects.equals(cityName, that.cityName) && Objects.equals(weatherForecastDataDto, that.weatherForecastDataDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, weatherForecastDataDto);
    }

    @Override
    public String toString() {
        return "WeatherForecastDto{" +
                "cityName='" + cityName + '\'' +
                ", weatherForecastDataDto=" + weatherForecastDataDto +
                '}';
    }
}
