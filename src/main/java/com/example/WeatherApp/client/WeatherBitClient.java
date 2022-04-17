package com.example.WeatherApp.client;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherBitClient {

private RestTemplate restTemplate = new RestTemplate();

public String getWeather(){
    return restTemplate.getForObject("https://api.weatherbit.io/v2.0/forecast/daily?" +
                    "lat=54.695&lon=18.678&key=5a3dfd7b3d8f4afc8e7af78e824064f4",
            String.class);
}

}
