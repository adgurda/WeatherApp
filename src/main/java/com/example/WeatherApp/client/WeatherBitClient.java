package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.City;
import com.example.WeatherApp.dto.WeatherBitDto;
import com.example.WeatherApp.dto.WeatherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherBitClient implements WeatherClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Mapper mapper = new Mapper();

    private final String host;
    private final String apiKey;

    public WeatherBitClient(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
    }

    @Override
    public String getWeather(City city) {
        String weatherBitDto = restTemplate.getForObject(
                host + "/v2.0/forecast/daily?lat={lat}&lon={lon}&key={key}", String.class,
                city.getLat(), city.getLon(), apiKey);
        WeatherDto weatherDto = mapper.objectMapper.readValue(weatherBitDto, WeatherDto.class);
        return weatherDto.;
    }

}
