package com.zenika.quarkus.workshop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDate;
import java.util.List;

@RegisterForReflection
public class Daily {

    private List<Double> temperatureMax;
    private List<Double> temperatureMin;
    private List<LocalDate> time;

    @JsonProperty("temperatureMax")
    public List<Double> getTemperatureMax() {
        return temperatureMax;
    }

    @JsonProperty("temperature_2m_max")
    void setTemperatureMax(List<Double> temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    @JsonProperty("temperatureMin")
    public List<Double> getTemperatureMin() {
        return temperatureMin;
    }

    @JsonProperty("temperature_2m_min")
    void setTemperatureMin(List<Double> temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public List<LocalDate> getTime() {
        return time;
    }

    void setTime(List<LocalDate> time) {
        this.time = time;
    }
}
