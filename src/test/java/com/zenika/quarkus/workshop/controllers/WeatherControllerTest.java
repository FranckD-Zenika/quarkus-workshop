package com.zenika.quarkus.workshop.controllers;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(WeatherController.class)
class WeatherControllerTest {

    @Test
    void findAll() {
        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .body(is("[{\"town\":\"londres\",\"temperature\":18.2},{\"town\":\"paris\",\"temperature\":17.5}]"));
    }

    @Test
    void find_existingTown() {
        given()
                .when()
                .get("/paris")
                .then()
                .statusCode(200)
                .body(is("{\"town\":\"paris\",\"temperature\":17.5}"));
    }

}