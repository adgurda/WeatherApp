package com.example.WeatherApp.dto;

public class WeatherBitDto {
    WeatherDtoData data;
    WeatherDtoCity cityName;

    public WeatherDtoData getData() {
        return data;
    }

    public WeatherDtoCity getCityName() {
        return cityName;
    }
}
