package com.example.WeatherApp.controller.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastDto {
    public final String cityName;
    public final List<DailyWeatherForecastDto> dailyWeatherForecastDto;

    public WeatherForecastDto(@JsonProperty("city_name") String cityName,
                              @JsonProperty("data")List<DailyWeatherForecastDto> dailyWeatherForecastDto) {
        this.cityName = cityName;
        this.dailyWeatherForecastDto = dailyWeatherForecastDto;
    }

    public Optional<DailyWeatherForecastDto> getDailyForecast(LocalDate date){
        return dailyWeatherForecastDto.stream()
                .filter(dailyForecast -> dailyForecast.date.equals(date))
                .findAny();
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
