package com.example.WeatherApp.controller;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.client.WeatherClient;
import com.example.WeatherApp.controller.dto.WeatherForecastDto;
import com.example.WeatherApp.exception.MappingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WeatherController {
    private final WeatherClient weatherClient;

    @Autowired
    public WeatherController(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
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

    @GetMapping("/daily-weather")
    ResponseEntity<WeatherResponse> getDailyWeather(
            @RequestParam("date") String date,
            @RequestParam("cityName") String city) throws MappingException{
        WeatherForecastDto weather = null;
        try {
            weather = weatherClient.getDailyForecast(City.valueOf(city.toUpperCase()), LocalDate.parse(date));
        } catch (JsonProcessingException e) {
            throw new MappingException("Problem with parsing / generating JSON");
        }
    return new ResponseEntity<>(toWeatherResponse(weather), HttpStatus.OK);

    }

    private WeatherResponse toWeatherResponse (WeatherForecastDto weatherForecastDto{

        /*
        WeatherResponse weatherDaily = weatherForecastDto.dailyWeatherForecastDto
                .stream()
                .map(daily -> new WeatherResponse(weatherForecastDto.cityName,
                        daily.date,
                        daily.maxTemperature,
                        daily.windSpeed,
                        daily.temperature,
                        daily.minTemperature));

         */
        /*
        WeatherResponse weatherResponse = (WeatherResponse) weatherForecastDto.dailyWeatherForecastDto
                .stream()
                .map(daily -> new WeatherResponse(
                        daily.temperature,
                        daily.date,
                        daily.windSpeed,
                        daily.maxTemperature,
                        daily.minTemperature));
        return weatherResponse;

         */

    }

}
