package com.example.WeatherApp.controller;

import com.example.WeatherApp.controller.dto.WeatherForGivenDay;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class BestWeatherSelector {

    public WeatherForGivenDay chooseBestWeather(List<WeatherForGivenDay> weathersForDateUsingStream) {

        WeatherForGivenDay bestWeatherForGivenDay = weathersForDateUsingStream.get(0);

        for (int i = 0; i < weathersForDateUsingStream.size(); i++) {
            WeatherForGivenDay weatherForGivenDay = weathersForDateUsingStream.get(i);
            for (int j = 1; j < weathersForDateUsingStream.size(); j++) {
                WeatherForGivenDay weatherForGivenDayj = weathersForDateUsingStream.get(j);
                if(weatherForGivenDayj.windSpeed > weatherForGivenDay.windSpeed){
                    bestWeatherForGivenDay = weatherForGivenDayj;
                }
            }
        }

        return bestWeatherForGivenDay;
    }

    public WeatherForGivenDay chooseBestWeatherWithStream(List<WeatherForGivenDay> weatherForGivenDays){
        return weatherForGivenDays.stream()
                .sorted(Comparator.comparing(WeatherForGivenDay::getWindSpeed).reversed()
                        .thenComparing(WeatherForGivenDay::getTemperature)).findFirst().orElseThrow();
    }

}
