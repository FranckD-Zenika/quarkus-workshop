package com.zenika.quarkus.workshop.entities;

import javax.validation.constraints.NotBlank;

public class Town {

    @NotBlank
    private final String name;
    private final double latitude;
    private final double longitude;

    private Town(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Town of(String town, double latitude, double longitude) {
        return new Town(town, latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
