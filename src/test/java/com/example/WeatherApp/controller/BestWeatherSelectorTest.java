package com.example.WeatherApp.controller;

import com.example.WeatherApp.controller.dto.WeatherForGivenDay;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BestWeatherSelectorTest {

    @Test
    void should_get_best_weather(){

        //given
        WeatherForGivenDay weatherForGivenDay1 = new WeatherForGivenDay(
                "Krakow",
                10.2F,
                LocalDate.of(2022, 05, 27),
                2.5f,
                15.6F,
                4.7F
        );

        WeatherForGivenDay weatherForGivenDay2 = new WeatherForGivenDay(
                "Ksiaznice",
                8.2F,
                LocalDate.of(2022, 05, 27),
                2.2f,
                13.6F,
                2.7F
        );

        BestWeatherSelector bestWeatherSelector = new BestWeatherSelector();

        //when
        WeatherForGivenDay result = bestWeatherSelector.chooseBestWeather(List.of(weatherForGivenDay1, weatherForGivenDay2));

        //then
        Assertions.assertThat(result).isEqualTo(weatherForGivenDay1);
    }
}