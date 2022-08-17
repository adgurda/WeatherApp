package com.example.WeatherApp.controller;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.client.WeatherClient;
import com.example.WeatherApp.controller.dto.DailyWeatherForecastDto;
import com.example.WeatherApp.controller.dto.WeatherForGivenDay;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Controller
public class WeatherController {
    private final WeatherClient weatherClient;

    private final BestWeatherSelector bestWeatherSelector;

    @Autowired
    public WeatherController(WeatherClient weatherClient, BestWeatherSelector bestWeatherSelector) {
        this.weatherClient = weatherClient;
        this.bestWeatherSelector = bestWeatherSelector;
    }

    @GetMapping("/weather")
    ResponseEntity<WeatherForecastDto> getWeather(@RequestParam("cityName") String city) throws MappingException {
        WeatherForecastDto weather = null;
        if (city.isBlank() || city.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            weather = weatherClient.getWeather(City.valueOf(city.toUpperCase()));
        } catch (JsonProcessingException e) {
            throw new MappingException("Problem with parsing / generating JSON");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }


    @GetMapping("/daily-weather")
    ResponseEntity<WeatherForGivenDay> getDailyWeather(
            @RequestParam("date") String date,
            @RequestParam("cityName") String city) throws MappingException {
        LocalDate localDate = LocalDate.parse(date);
        WeatherForecastDto weather = null;
        try {
            weather = weatherClient.getDailyForecast(City.valueOf(city.toUpperCase()), localDate);
        } catch (JsonProcessingException e) {
            throw new MappingException("Problem with parsing / generating JSON");
        }
        WeatherForGivenDay weatherForGivenDay = getWeatherForDate(weather, localDate);
        return new ResponseEntity<>(weatherForGivenDay, HttpStatus.OK);
    }



    @GetMapping("/best-weather")
    ResponseEntity<WeatherForGivenDay> bestWeather(
            @RequestParam("date") String date)
            throws MappingException {
        LocalDate localDate = LocalDate.parse(date);
        List<WeatherForecastDto> weathers = null;
        try {
            weathers = Arrays.stream(City.values()).map(city -> {
                try {
                    return weatherClient.getWeather(city);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<WeatherForGivenDay> weathersForDateUsingStream = getWeathersForDateUsingStream(weathers, localDate);

        WeatherForGivenDay weatherForGivenDay = bestWeatherSelector.chooseBestWeather(weathersForDateUsingStream);

        return new ResponseEntity<>(weatherForGivenDay,HttpStatus.OK);
    }

    private List<WeatherForGivenDay> getWeathersForDate(List<WeatherForecastDto> weathers, LocalDate date) {

        List<WeatherForGivenDay> weathersForGivenDay = new ArrayList<>();

        for (WeatherForecastDto weather : weathers) {
            WeatherForGivenDay weatherForDate = getWeatherForDate(weather, date);
            weathersForGivenDay.add(weatherForDate);
        }

        return weathersForGivenDay;
    }

    private List<WeatherForGivenDay> getWeathersForDateUsingStream(List<WeatherForecastDto> weathers, LocalDate date) {

        List<WeatherForGivenDay> weathersForGivenDay = weathers.stream()
                .map(weatherForecastDto -> getWeatherForDate(weatherForecastDto, date))
                .collect(Collectors.toList());

        return weathersForGivenDay;
    }

    private DailyWeatherResponse toWeatherResponse(DailyWeatherForecastDto dailyWeatherForecastDto, String cityName) {
        return new DailyWeatherResponse(cityName,
                dailyWeatherForecastDto.temperature,
                dailyWeatherForecastDto.windSpeed,
                dailyWeatherForecastDto.maxTemperature,
                dailyWeatherForecastDto.minTemperature);
    }

    private WeatherForGivenDay getWeatherForGivenDay (WeatherForecastDto weatherForecastDto, LocalDate date){
        DailyWeatherForecastDto dailyWeatherForecastDto;

        dailyWeatherForecastDto = weatherForecastDto.dailyWeatherForecastDto
                .stream()
                .filter(daily -> daily.date.equals(date))
                .findFirst()
                .orElseThrow();

        return new WeatherForGivenDay(
                weatherForecastDto.cityName,
                dailyWeatherForecastDto.temperature,
                dailyWeatherForecastDto.date,
                dailyWeatherForecastDto.windSpeed,
                dailyWeatherForecastDto.maxTemperature,
                dailyWeatherForecastDto.minTemperature
        );
    }

    private WeatherForGivenDay getWeatherForDate(WeatherForecastDto weatherForecastDto, LocalDate date) {
        DailyWeatherForecastDto dailyWeatherForecastDto;

        dailyWeatherForecastDto = weatherForecastDto.dailyWeatherForecastDto
                .stream()
                .filter(daily -> daily.date.equals(date))
                .findFirst()
                .orElseThrow();

        return new WeatherForGivenDay(
                weatherForecastDto.cityName,
                dailyWeatherForecastDto.temperature,
                dailyWeatherForecastDto.date,
                dailyWeatherForecastDto.windSpeed,
                dailyWeatherForecastDto.maxTemperature,
                dailyWeatherForecastDto.minTemperature
        );
    }

    private WeatherForGivenDay getBestWeatherForDate(List<WeatherForGivenDay> weathers) {

        return weathers
                .stream()
                .max(Comparator.comparing(WeatherForGivenDay::getWindSpeed)).orElseThrow();
    }

}
