package io.swagger.petstore.controller;

import io.restassured.response.Response;
import io.swagger.petstore.dto.PetDto;

public class PetController extends BaseController {

    public Response addPet(final PetDto petDto) {
        return getRequestSpecification()
                .body(petDto)
                .post("/pet");
    }

    public Response getPetById(final long petId) {
        return getRequestSpecification()
                .pathParam("petId", petId)
                .get("/pet/{petId}");
    }

}
