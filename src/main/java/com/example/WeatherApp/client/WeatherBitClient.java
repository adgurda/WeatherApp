package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.dto.DailyWeatherForecastDto;
import com.example.WeatherApp.dto.WeatherForecastDto;
import com.example.WeatherApp.exception.MappingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherBitClient implements WeatherClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String host;
    private final String apiKey;
    private final ObjectMapper mapper;
    public WeatherBitClient(String host, String apiKey, ObjectMapper mapper) {
        this.host = host;
        this.apiKey = apiKey;
        this.mapper = mapper;
    }

    @Override
    public WeatherForecastDto getWeather(City city) throws MappingException {
        String weather = restTemplate.getForObject(
                host + "/v2.0/forecast/daily?lat={lat}&lon={lon}&key={key}", String.class,
                city.getLat(), city.getLon(), apiKey);
        WeatherForecastDto weatherDto = null;
        try {
            weatherDto = mapper.readValue(weather, WeatherForecastDto.class);
        } catch (JsonProcessingException e) {
            throw new MappingException("Problem with parsing / generating JSON");
        }
        return weatherDto;
    }

    @Override
    public List<DailyWeatherForecastDto> getDailyForecast(City city, LocalDate date)
            throws JsonProcessingException, MappingException {
        String weather = restTemplate.getForObject(
                host + "/v2.0/forecast/daily?lat={lat}&lon={lon}&key={key}", String.class,
                city.getLat(), city.getLon(), apiKey);
        var result =  mapper.readValue(weather, DailyWeatherForecastDto.class);
        List<DailyWeatherForecastDto> dailyWeather = null;
        dailyWeather.add(result);
        dailyWeather.stream().filter(forecast -> forecast.date.isEqual(date)).collect(Collectors.toList());
        return dailyWeather;
    }
}
