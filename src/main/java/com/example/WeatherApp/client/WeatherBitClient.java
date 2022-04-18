package com.example.WeatherApp.client;

import com.example.WeatherApp.cities.CityInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherBitClient {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String WEATHER_URL = "http://api.weatherbit.io/v2.0/forecast/daily?";
    private static final String APP_KEY = "5a3dfd7b3d8f4afc8e7af78e824064f4";

    public String getWeatherJastarnia() {
        return restTemplate.getForObject(WEATHER_URL + "lat={lat}&lon={lon}&key={key}", String.class,
                CityInfo.JASTARNIA_LAT.getCoord(),
                CityInfo.JASTARNIA_LON.getCoord(), APP_KEY);
    }

    public String getWeatherBridgetown() {
        return restTemplate.getForObject(WEATHER_URL + "lat={lat}&lon={lon}&key={key}", String.class,
                CityInfo.BRIDGETOWN_LAT.getCoord(),
                CityInfo.BRIDGETOWN_LON.getCoord(), APP_KEY);
    }

    public String getWeatherFortaleza() {
        return restTemplate.getForObject(WEATHER_URL + "lat={lat}&lon={lon}&key={key}", String.class,
                CityInfo.FORTALEZA_LAT.getCoord(),
                CityInfo.FORTALEZA_LON.getCoord(), APP_KEY);
    }

    public String getWeatherLemarne() {
        return restTemplate.getForObject(WEATHER_URL + "lat={lat}&lon={lon}&key={key}", String.class,
                CityInfo.LE_MARNE_LAT.getCoord(),
                CityInfo.LE_MARNE_LON.getCoord(), APP_KEY);
    }

    public String getWeatherPissouri() {
        return restTemplate.getForObject(WEATHER_URL + "lat={lat}&lon={lon}&key={key}", String.class,
                CityInfo.PISSOURI_LAT.getCoord(),
                CityInfo.PISSOURI_LON.getCoord(), APP_KEY);
    }

}
