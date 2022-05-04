package com.example.WeatherApp;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

public class IntegrationTest {

    public static WireMockServer wm;

    static {
        wm = new WireMockServer();
        wm.start();
        configureFor("localhost", 8080);
    }
}
