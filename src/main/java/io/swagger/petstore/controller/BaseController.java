package io.swagger.petstore.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseController {

    protected static RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }

}
