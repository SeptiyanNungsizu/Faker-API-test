package com.fakerapi.automation.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.fakerapi.automation.model.ApiResponse;

import java.util.Map;

public class PersonController {

    private static final String BASE_URL = "https://fakerapi.it";
    private static final String PERSONS_ENDPOINT = "/api/v2/persons";

    public ApiResponse getPersons(Map<String, ?> queryParams) {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .queryParams(queryParams)
                .when()
                .get(PERSONS_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.as(ApiResponse.class);
    }
}
