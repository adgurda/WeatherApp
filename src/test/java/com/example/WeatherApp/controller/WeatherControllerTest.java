package com.example.WeatherApp.controller;

import com.example.WeatherApp.dto.WeatherForecastDataDto;
import com.example.WeatherApp.exception.MappingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class WeatherControllerTest {
    @Test
    void should_throw_execption_when_parsing_to_json() {
        //nie działa
        assertThatThrownBy(() -> new WeatherForecastDataDto(-10.4F, "byleco", 12.2F))
                .isInstanceOf(MappingException.class).hasMessage("Problem with parsing / generating JSON");
    }
}