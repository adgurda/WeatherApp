package com.example.WeatherApp;

import com.example.WeatherApp.client.WeatherBitClient;
import com.example.WeatherApp.client.WeatherClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@TestConfiguration
@PropertySource("classpath:application-test.properties")
public class TestAppConfiguration {

    @Bean
    @Profile(value = "test")
    public WeatherClient weatherClient() {
        return new WeatherBitClientStub();
    }
}
