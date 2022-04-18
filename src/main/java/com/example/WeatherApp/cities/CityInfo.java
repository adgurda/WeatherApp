package com.example.WeatherApp.cities;

public enum CityInfo {

    JASTARNIA_LON("8.678"),
    JASTARNIA_LAT("54.695"),

    BRIDGETOWN_LON("-59.603"),
    BRIDGETOWN_LAT("13.103"),

    FORTALEZA_LON("-38.526"),
    FORTALEZA_LAT("-3.731"),

    PISSOURI_LON("32.701"),
    PISSOURI_LAT("34.669"),

    LE_MORNE_LON("57.328"),
    LE_MORNE_LAT("-20.445");

    private String coord;

    CityInfo(String coord) {
        this.coord = coord;
    }

    public String getCoord() {
        return coord;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "coord='" + coord + '\'' +
                '}';
    }
}
