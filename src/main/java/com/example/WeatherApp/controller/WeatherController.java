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


    @GetMapping("/weather/Jastarnia")
    ResponseEntity<String> getWeatherJastarnia(){
        weatherBitClient.getWeatherJastarnia();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/weather/Bridgetown")
    ResponseEntity<String> getWeatherBridgetown(){
        weatherBitClient.getWeatherBridgetown();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/weather/Fortaleza")
    ResponseEntity<String> getWeatherFortaleza(){
        weatherBitClient.getWeatherFortaleza();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/weather/Lemarne")
    ResponseEntity<String> getWeatherLemarne(){
        weatherBitClient.getWeatherLemarne();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/weather/Pissouri")
    ResponseEntity<String> getWeatherPissouri(){
        weatherBitClient.getWeatherPissouri();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
