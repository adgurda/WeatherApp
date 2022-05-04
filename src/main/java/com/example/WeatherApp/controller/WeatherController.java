package com.example.WeatherApp.controller;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.client.WeatherBitClient;
import com.example.WeatherApp.client.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {
    private final WeatherClient weatherClient;

    @Autowired
    public WeatherController(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @GetMapping("/weather")
    ResponseEntity<String> getWeather(@RequestParam("cityName") String city) {
        String weather = weatherClient.getWeather(City.valueOf(city.toUpperCase())); // TODO zmien stringa na typ DTO
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

}
