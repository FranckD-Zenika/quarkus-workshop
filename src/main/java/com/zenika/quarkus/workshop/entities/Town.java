package com.zenika.quarkus.workshop.entities;

import javax.validation.constraints.NotBlank;

public class Town {

    @NotBlank
    private final String town;

    private final double latitude;
    private final double longitude;

    private Town(String town, double latitude, double longitude) {
        this.town = town;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Town of(String town, double latitude, double longitude) {
        return new Town(town, latitude, longitude);
    }

    public String getTown() {
        return town;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
