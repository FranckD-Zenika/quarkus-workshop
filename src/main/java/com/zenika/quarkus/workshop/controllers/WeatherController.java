package com.zenika.quarkus.workshop.controllers;

import com.zenika.quarkus.workshop.entities.Town;
import com.zenika.quarkus.workshop.entities.Weather2;
import com.zenika.quarkus.workshop.services.WeatherService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
    public Multi<Weather2> findAll() {
        return weatherService.findAll();
    }

    @GET
    @Path("/{name}")
    public Uni<Town> find(@RestPath String name) {
        return weatherService.find(name);
    }

    @GET
    @Path("/{name}/weather")
    public Uni<Weather2> findWeather(@RestPath String name) {
        return weatherService.findWeather(name);
    }

    @POST
    public Uni<Response> create(@Valid Town town) {
        return weatherService.create(town)
                .map(unused -> Response.created(buildUri(WeatherController.class, town.getTown()))
                        .entity(town)
                        .build());
    }

}
