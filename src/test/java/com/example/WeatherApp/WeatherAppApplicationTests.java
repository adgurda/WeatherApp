package com.example.WeatherApp;

import com.example.WeatherApp.controller.DailyWeatherResponse;
import com.example.WeatherApp.controller.WeatherResponse;
import com.example.WeatherApp.controller.dto.DailyWeatherForecastDto;
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
                        LocalDate.parse("2022-05-06"),
                        2.5F,
                        11.2F,
                        5.7F)
                );
    }

    @Test
    void should_return_bad_request_when_cityName_is_blanc() {
        ResponseEntity<WeatherForecastDto> response = restTemplate
                .getForEntity("http://localhost:" + port + "/weather?cityName=", WeatherForecastDto.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    //TODO 1.weatherbit odpowiada bledem (500)?
    //TODO 2.uzupełnić dto o nowe pola ktore beda potrzebne (mail)
    //TODO 3.stowrzyc endpoint zgodny ze specyfikacja w mail (przyjmuje date)
    //TODO 4.zwrocic dane pogodowe w danym dniu w Jastarnia (cityname, temperature, windSpeed)
    //TODO 5.wyciagnac z danego dnia informacje o pogodzie (stream)

    @Test
    void return_daily_daily_weather_by_cityName_and_date() {
        ResponseEntity<DailyWeatherResponse> response = restTemplate
                .getForEntity("http://localhost:" + port + "/daily-weather?date=2022-05-24&cityName=Jastarnia",
                        DailyWeatherResponse.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

}
