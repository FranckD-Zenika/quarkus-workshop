package com.zenika.quarkus.workshop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Weather2 {

    private DailyUnits units;
    private Daily daily;
    private int elevation;
    private double longitude;
    private double latitude;

    public Daily getDaily() {
        return daily;
    }

    void setDaily(Daily daily) {
        this.daily = daily;
    }

    public int getElevation() {
        return elevation;
    }

    void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public double getLongitude() {
        return longitude;
    }

    void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("units")
    public DailyUnits getUnits() {
        return units;
    }

    @JsonProperty("daily_units")
    public void setUnits(DailyUnits units) {
        this.units = units;
    }
}
