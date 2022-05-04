package com.example.WeatherApp;

import com.example.WeatherApp.client.WeatherBitClient;
import com.example.WeatherApp.client.WeatherClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Bean
    public WeatherClient weatherClient(@Value("${weatherbit.client.host}") String host,
                                       @Value("${weatherbit.client.apikey}") String apiKey) {
        return new WeatherBitClient(host, apiKey);
    }
}
