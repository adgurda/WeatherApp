package com.example.WeatherApp.controller;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.client.WeatherClient;
import com.example.WeatherApp.controller.dto.DailyWeatherForecastDto;
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    ResponseEntity<DailyWeatherResponse> getDailyWeather(
            @RequestParam("date") String date,
            @RequestParam("cityName") String city) throws MappingException{
        LocalDate localDate = LocalDate.parse(date);
        WeatherForecastDto weather = null;
        try {
            weather = weatherClient.getDailyForecast(City.valueOf(city.toUpperCase()), localDate);
        } catch (JsonProcessingException e) {
            throw new MappingException("Problem with parsing / generating JSON");
        }
        DailyWeatherForecastDto dailyWeatherForecastDto = getWeatherForDate(weather, localDate);
    return new ResponseEntity<>(toWeatherResponse(dailyWeatherForecastDto, city), HttpStatus.OK);

    }

    @GetMapping("/best-weather")
    ResponseEntity<DailyWeatherResponse> bestWeather(
            @RequestParam("date") String date)
            throws MappingException{
        LocalDate localDate = LocalDate.parse(date);
        List<WeatherForecastDto> weathers = null;
        try {
            weathers = Arrays.stream(City.values()).map(city -> {
                try {
                    return weatherClient.getWeather(city);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        weathers.forEach(element -> element.dailyWeatherForecastDto
                .forEach(daily -> ));

        DailyWeatherForecastDto getWeatherByDate = getWeatherForDate(weathers.get(4), localDate);



        return new ResponseEntity<>(toWeatherResponse(getWeatherByDate, weathers.get(4).cityName), HttpStatus.OK);
    }

    private DailyWeatherResponse toWeatherResponse (DailyWeatherForecastDto dailyWeatherForecastDto, String cityName){
        return new DailyWeatherResponse(cityName,
                dailyWeatherForecastDto.temperature,
                dailyWeatherForecastDto.windSpeed,
                dailyWeatherForecastDto.maxTemperature,
                dailyWeatherForecastDto.minTemperature);
    }

    private DailyWeatherForecastDto getWeatherForDate(WeatherForecastDto weatherForecastDto, LocalDate date) {
        DailyWeatherForecastDto dailyWeatherForecastDto;

        dailyWeatherForecastDto =  weatherForecastDto.dailyWeatherForecastDto
                .stream()
                .filter(daily -> daily.date.equals(date)).findFirst().orElseThrow();

        return dailyWeatherForecastDto;
    }

    private DailyWeatherForecastDto getBestWeatherForDate(List<DailyWeatherForecastDto> weathers){

        return weathers
                .stream()
                .max(Comparator.comparing(DailyWeatherForecastDto::getWindSpeed)).orElseThrow();
    }

}
