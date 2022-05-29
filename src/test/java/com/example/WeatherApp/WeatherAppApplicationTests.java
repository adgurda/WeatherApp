package com.example.WeatherApp;

import com.example.WeatherApp.controller.DailyWeatherResponse;
import com.example.WeatherApp.controller.WeatherResponse;
import com.example.WeatherApp.controller.dto.DailyWeatherForecastDto;
import com.example.WeatherApp.controller.dto.WeatherForGivenDay;
import com.example.WeatherApp.controller.dto.WeatherForecastDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class WeatherAppApplicationTests extends IntegrationTest {
    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnWeather() {
        ResponseEntity<WeatherForecastDto> response = restTemplate
                .getForEntity("http://localhost:" + port + "/weather?cityName=Jastarnia", WeatherForecastDto.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().cityName).isEqualTo("Jastarnia");
        assertThat(response.getBody().dailyWeatherForecastDto).
                containsExactly(new DailyWeatherForecastDto(
                        10.2F,
                        LocalDate.of(2022, 5, 21),
                        2.5F,
                        11.2F,
                        5.7F
                ));
        //działa
    }

    @Test
    void should_return_bad_request_when_cityName_is_blanc() {
        ResponseEntity<WeatherForecastDto> response = restTemplate
                .getForEntity("http://localhost:" + port + "/weather?cityName=", WeatherForecastDto.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(400);

        //działa
    }

    @Test
    void return_daily_daily_weather_by_cityName_and_date() {
        ResponseEntity<WeatherForGivenDay> response = restTemplate
                .getForEntity("http://localhost:" + port + "/daily-weather?date=2022-05-21&cityName=Jastarnia",
                        WeatherForGivenDay.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().date).isEqualTo(LocalDate.of(2022, 5, 21));
        assertThat(response.getBody().cityName).isEqualTo("Jastarnia");
        assertThat(response.getBody().temperature).isEqualTo(10.2F);
        assertThat(response.getBody().minTemperature).isEqualTo(5.7F);
        assertThat(response.getBody().maxTemperature).isEqualTo(11.2F);
        assertThat(response.getBody().windSpeed).isEqualTo(2.5F);
        //działa

    }

}
