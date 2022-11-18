package com.zenika.quarkus.workshop.controllers;

import com.zenika.quarkus.workshop.entities.Town;
import com.zenika.quarkus.workshop.entities.Weather2;
import com.zenika.quarkus.workshop.services.WeatherService;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.zenika.quarkus.workshop.utils.UriUtils.buildUri;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/weathers")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class WeatherController {

    private final WeatherService weatherService;

    @Inject
    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GET
    public Map<String, Weather2> findAll() {
        return weatherService.findAll();
    }

    @GET
    @Path("/{name}")
    public Town find(@RestPath String name) {
        return weatherService.find(name);
    }

    @GET
    @Path("/{name}/weather")
    public Weather2 findWeather(@RestPath String name) {
        return weatherService.findWeather(name);
    }

    @POST
    public Response create(@Valid Town town) {
        weatherService.create(town);
        return Response.created(buildUri(WeatherController.class, town.getName()))
                .entity(town)
                .build();
    }

}
