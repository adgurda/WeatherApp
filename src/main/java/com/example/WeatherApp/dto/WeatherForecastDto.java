package com.example.WeatherApp.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastDto {
    public final String cityName;
    public final List<DailyWeatherForecastDto> dailyWeatherForecastDto;

    public WeatherForecastDto(@JsonProperty("city_name") String cityName,
                              @JsonProperty("data")List<DailyWeatherForecastDto> dailyWeatherForecastDto) {
        this.cityName = cityName;
        this.dailyWeatherForecastDto = dailyWeatherForecastDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecastDto that = (WeatherForecastDto) o;
        return Objects.equals(cityName, that.cityName) && Objects.equals(dailyWeatherForecastDto, that.dailyWeatherForecastDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, dailyWeatherForecastDto);
    }

    @Override
    public String toString() {
        return "WeatherForecastDto{" +
                "cityName='" + cityName + '\'' +
                ", weatherForecastDataDto=" + dailyWeatherForecastDto +
                '}';
    }
}
