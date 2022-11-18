package com.zenika.quarkus.workshop.services;

import com.zenika.quarkus.workshop.entities.Town;
import com.zenika.quarkus.workshop.entities.Weather2;
import com.zenika.quarkus.workshop.exceptions.ConflictException;
import com.zenika.quarkus.workshop.restclients.WeatherClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public interface WeatherService {

    Map<String, Weather2> findAll();
    Town find(String town);
    Weather2 findWeather(String name);
    void create(Town town);

    @ApplicationScoped
    class DefaultWeatherService implements WeatherService {

        private static final Map<String, Town> towns = new HashMap<>();
        static {
            towns.put("paris", Town.of("paris", 48.856614, 2.3522219));
            towns.put("londres", Town.of("londres", 51.5073509, -0.1277583));
        }

        private final WeatherClient weatherClient;

        @Inject
        DefaultWeatherService(@RestClient WeatherClient weatherClient) {
            this.weatherClient = weatherClient;
        }

        @Override
        public Map<String, Weather2> findAll() {
                    return towns.values().stream()
                    .map(town -> Map.entry(town.getName(), weatherClient.getWeather(List.of("temperature_2m_min","temperature_2m_max"), town.getLongitude(), town.getLatitude(), "CET")))
                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        }

        @Override
        public Town find(String name) {
            return Optional.ofNullable(towns.get(name))
                    .orElseThrow(NotFoundException::new);
        }

        @Override
        public Weather2 findWeather(String name) {
            var town = find(name);
            return weatherClient.getWeather(List.of("temperature_2m_min","temperature_2m_max"), town.getLongitude(), town.getLatitude(), "CET");
        }

        @Override
        public void create(Town town) {
            if (towns.containsKey(town.getName().toLowerCase()))
                throw new ConflictException();
            towns.put(town.getName(), town);
        }
    }

}
