package com.example.WeatherApp.cities;

public enum City {

    JASTARNIA("Jastarnia", 8.678, 54.695),
    BRIDGETOWN("Bridgetown",-59.603, 13.103),
    FORTALEZA("Fortaleza",-38.526, 13.103),
    PISSOURI("Pissouri", 32.701, 34.669),
    LE_MORNE("Le Morne",57.328, -20.445);


    private double lon;
    private double lat;
    private String name;

    City(String name, double lon, double lat) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" +
                "lon=" + lon +
                ", lat=" + lat +
                ", name='" + name + '\'' +
                '}';
    }
}
