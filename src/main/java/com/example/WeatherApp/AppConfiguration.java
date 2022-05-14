package com.example.WeatherApp;

import com.example.WeatherApp.client.WeatherBitClient;
import com.example.WeatherApp.client.WeatherClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Bean
    public WeatherClient weatherClient(@Value("${weatherbit.client.host}") String host,
                                       @Value("5a3dfd7b3d8f4afc8e7af78e824064f4") String apiKey,
                                       ObjectMapper mapper) {
        return new WeatherBitClient(host, apiKey, mapper);
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(module);
    }
}
