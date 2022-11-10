package com.zenika.quarkus.workshop.controllers;

import com.zenika.quarkus.workshop.entities.Weather;
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
import java.util.Collection;

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
    public Collection<Weather> findAll() {
        return weatherService.findAll();
    }

    @GET
    @Path("/{town}")
    public Weather find(@RestPath String town) {
        return weatherService.find(town);
    }

    @POST
    public Response create(@Valid Weather weather) {
        weatherService.create(weather);
        return Response.created(buildUri(WeatherController.class, weather.getTown()))
                .entity(weather)
                .build();
    }

}
