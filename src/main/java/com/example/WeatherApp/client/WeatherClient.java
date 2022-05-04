package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.City;

public interface WeatherClient {

    String getWeather(City city);
}
