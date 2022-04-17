package com.example.WeatherApp.controller;

import com.example.WeatherApp.client.WeatherBitClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {
    private final WeatherBitClient weatherBitClient;

    @Autowired
    public WeatherController(WeatherBitClient weatherBitClient) {
        this.weatherBitClient = weatherBitClient;
    }


    @GetMapping("/weather")
    ResponseEntity<String> getWeather(){
        weatherBitClient.getWeather();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
