package com.zenika.quarkus.workshop.services;

import com.zenika.quarkus.workshop.entities.Weather;
import com.zenika.quarkus.workshop.exceptions.ConflictException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface WeatherService {

    Collection<Weather> findAll();
    Weather find(String town);

    void create(Weather weather);

    @ApplicationScoped
    class DefaultWeatherService implements WeatherService {

        private static final Map<String, Weather> weathers = new HashMap<>();
        static {
            weathers.put("paris", Weather.of("paris", 17.5));
            weathers.put("londres", Weather.of("londres", 18.2));
        }

        @Override
        public Collection<Weather> findAll() {
            return weathers.values();
        }

        @Override
        public Weather find(String town) {
            return Optional.ofNullable(weathers.get(town))
                    .orElseThrow(NotFoundException::new);
        }

        @Override
        public void create(Weather weather) {
            if (weathers.containsKey(weather.getTown().toLowerCase()))
                throw new ConflictException();
            weathers.put(weather.getTown(), weather);
        }
    }

}
