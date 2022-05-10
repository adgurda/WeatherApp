package com.example.WeatherApp;

import com.example.WeatherApp.dto.WeatherForecastDataDto;
import com.example.WeatherApp.dto.WeatherForecastDto;
import com.example.WeatherApp.exception.MappingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.DOUBLE;

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
		assertThat(response.getBody().getCityName()).isEqualTo("Jastarnia");
		assertThat(response.getBody().getWeatherForecastDataDto()).
				containsExactly(new WeatherForecastDataDto(10.2F, "2022-05-06", 2.5F));
	}

	@Test
	void should_return_bad_request_when_cityName_is_blanc() {
		ResponseEntity<WeatherForecastDto> response = restTemplate
				.getForEntity("http://localhost:" + port + "/weather?cityName=", WeatherForecastDto.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

}
