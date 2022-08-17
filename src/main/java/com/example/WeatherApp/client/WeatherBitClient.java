package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.controller.dto.WeatherForecastDto;
import com.example.WeatherApp.exception.MappingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static java.nio.file.attribute.AclEntry.newBuilder;

public class WeatherBitClient implements WeatherClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String host;
    private final String apiKey;
    private final ObjectMapper mapper;

    LoadingCache<City, WeatherForecastDto> cache =
            CacheBuilder.newBuilder()
                    .maximumSize(100)                             // maximum 100 records can be cached
                    .expireAfterWrite(30, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
                    .build(new CacheLoader<City, WeatherForecastDto>() {
                        @Override
                        public WeatherForecastDto load(City key) throws Exception {
                            return getWeatherForecastDto(key);
                        }  // build the cacheloader
                    });


    public WeatherBitClient(String host, String apiKey, ObjectMapper mapper) {
        this.host = host;
        this.apiKey = apiKey;
        this.mapper = mapper;
    }


    @Override
    public WeatherForecastDto getWeather(City city) throws MappingException, ExecutionException {
        WeatherForecastDto weatherDto = cache.get(city);
        return weatherDto;
    }

    private WeatherForecastDto getWeatherForecastDto(City city) {
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


    /*
    private String getWeatherWithCache(City city) {
        String weatherFromCache = cache.get(city);
        if(weatherFromCache == null) {
            return restTemplate.getForObject(
                    host + "/v2.0/forecast/daily?lat={lat}&lon={lon}&key={key}", String.class,
                    city.getLat(), city.getLon(), apiKey);
        }
        else return weatherFromCache;

        //TODO test jest pogoda w mapie to nie robie calla, nie ma robie call
    }


     */


    @Override
    public WeatherForecastDto getDailyForecast(City city, LocalDate date)
            throws JsonProcessingException, MappingException {
        String weather = restTemplate.getForObject(
                host + "/v2.0/forecast/daily?lat={lat}&lon={lon}&key={key}", String.class,
                city.getLat(), city.getLon(), apiKey);
        return mapper.readValue(weather, WeatherForecastDto.class);
    }


}

