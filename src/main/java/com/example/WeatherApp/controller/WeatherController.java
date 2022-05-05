package com.example.WeatherApp.controller;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.client.WeatherBitClient;
import com.example.WeatherApp.client.WeatherClient;
import com.example.WeatherApp.dto.WeatherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {
    private final WeatherClient weatherClient;
    private final WeatherBitClient weatherBitClient;

    @Autowired
    public WeatherController(WeatherClient weatherClient, WeatherBitClient weatherBitClient) {
        this.weatherClient = weatherClient;
        this.weatherBitClient = weatherBitClient;
    }

    @GetMapping("/weather")
    ResponseEntity<WeatherDto> getWeather(@RequestParam("cityName") String city) {
        WeatherDto weather = null; // TODO zmien stringa na typ DTO
        try {
            weather = weatherClient.getWeather(City.valueOf(city.toUpperCase()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

}
