package com.zenika.quarkus.workshop.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Weather {

    @NotBlank
    private final String town;
    @Min(-50L)
    @Max(50L)
    private final double temperature;

    private Weather(String town, double temperature) {
        this.town = town;
        this.temperature = temperature;
    }

    public static Weather of(String town, double temperature) {
        return new Weather(town, temperature);
    }

    public String getTown() {
        return town;
    }

    public double getTemperature() {
        return temperature;
    }
}
