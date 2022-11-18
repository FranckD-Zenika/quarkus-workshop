package com.zenika.quarkus.workshop.services;

import com.zenika.quarkus.workshop.entities.Town;
import com.zenika.quarkus.workshop.entities.Weather2;
import com.zenika.quarkus.workshop.exceptions.ConflictException;
import com.zenika.quarkus.workshop.restclients.WeatherClient;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WeatherService {

    Multi<Weather2> findAll();
    Uni<Town> find(String town);
    Uni<Weather2> findWeather(String name);
    Uni<Void> create(Town town);

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
        public Multi<Weather2> findAll() {
            return Multi.createFrom().iterable(towns.values())
                    .onItem()
                    .transformToUni(town -> weatherClient.getWeather(List.of("temperature_2m_min","temperature_2m_max"), town.getLongitude(), town.getLatitude(), "CET"))
                    .merge();
//            var x = Uni.createFrom().item(towns.values())
//                    .
//                            .flatMap(town -> weatherClient.getWeather(List.of("temperature_2m_min","temperature_2m_max"), town.getLongitude(), town.getLatitude(), "CET"))
//
//
//                    towns.values().stream()
//                    .map(town -> Map.entry(town.getTown(), weatherClient.getWeather(List.of("temperature_2m_min","temperature_2m_max"), town.getLongitude(), town.getLatitude(), "CET")))
//                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        }

        @Override
        public Uni<Town> find(String name) {
            return Optional.ofNullable(towns.get(name))
                    .map(Uni.createFrom()::item)
                    .orElseThrow(NotFoundException::new);
        }

        @Override
        public Uni<Weather2> findWeather(String name) {
            return find(name)
                    .flatMap(town -> weatherClient.getWeather(List.of("temperature_2m_min","temperature_2m_max"), town.getLongitude(), town.getLatitude(), "CET"));
        }

        @Override
        public Uni<Void> create(Town town) {
            if (towns.containsKey(town.getTown().toLowerCase()))
                throw new ConflictException();
            towns.put(town.getTown(), town);
            return Uni.createFrom().voidItem();
        }
    }

}
