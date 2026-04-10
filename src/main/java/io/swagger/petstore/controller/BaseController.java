package io.swagger.petstore.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseController<T> {

    private String token;

    protected RequestSpecification getRequestSpecification() {
        final RequestSpecification requestSpecification = RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);

        if (token != null) {
            requestSpecification.header("Authorization", token);
        }

        return requestSpecification;
    }

    @SuppressWarnings("unchecked")
    public T withToken(final String token) {
        this.token = token;
        return (T) this;
    }

}
