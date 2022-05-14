package com.example.WeatherApp.controller;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.client.WeatherBitClient;
import com.example.WeatherApp.client.WeatherClient;
import com.example.WeatherApp.dto.DailyWeatherForecastDto;
import com.example.WeatherApp.dto.WeatherForecastDto;
import com.example.WeatherApp.exception.MappingException;
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

    @Autowired
    public WeatherController(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;;
    }



    @GetMapping("/weather")
    ResponseEntity<WeatherForecastDto> getWeather(@RequestParam("cityName") String city) throws MappingException {
        WeatherForecastDto weather = null;
        if(city.isBlank() || city.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            weather = weatherClient.getWeather(City.valueOf(city.toUpperCase()));
        } catch (JsonProcessingException e) {
            throw new MappingException("Problem with parsing / generating JSON");
        }
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

    public WeatherResponse toWeatherResponse (DailyWeatherForecastDto dailyWeatherForecastDto){
        return new WeatherResponse(
                dailyWeatherForecastDto.temperature,
                dailyWeatherForecastDto.date,
                dailyWeatherForecastDto.windSpeed,
                dailyWeatherForecastDto.maxTemperature,
                dailyWeatherForecastDto.minTemperature);
    }

}
