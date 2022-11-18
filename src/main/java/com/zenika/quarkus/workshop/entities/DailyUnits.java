package com.zenika.quarkus.workshop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyUnits {

    private String temperatureMin;
    private String temperatureMax;
    private String time;

    @JsonProperty("temperatureMin")
    public String getTemperatureMin() {
        return temperatureMin;
    }

    @JsonProperty("temperature_2m_min")
    void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    @JsonProperty("temperatureMax")
    public String getTemperatureMax() {
        return temperatureMax;
    }

    @JsonProperty("temperature_2m_max")
    void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getTime() {
        return time;
    }

    void setTime(String time) {
        this.time = time;
    }
}
