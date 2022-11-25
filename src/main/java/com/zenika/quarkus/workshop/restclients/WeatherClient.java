package com.zenika.quarkus.workshop.restclients;

import com.zenika.quarkus.workshop.entities.Weather2;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import java.util.List;

@RegisterRestClient(configKey = "weather")
public interface WeatherClient {

    @GET
    @Retry
    @CircuitBreaker
    @Timeout(500L)
    Weather2 getWeather(@QueryParam("daily") List<String> wantedInfo,
                        @QueryParam("longitude") double longitude,
                        @QueryParam("latitude") double latitude,
                        @QueryParam("timezone") String timezone);
}
