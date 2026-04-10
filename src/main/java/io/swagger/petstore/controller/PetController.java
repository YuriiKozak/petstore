package io.swagger.petstore.controller;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.swagger.petstore.dto.PetDto;

import java.io.File;

public class PetController extends BaseController<PetController> {

    public Response uploadFile(final long petId, final String additionalMetadata, final File file) {
        return getRequestSpecification()
                .contentType(ContentType.MULTIPART)
                .multiPart("additionalMetadata", additionalMetadata)
                .multiPart("file", file)
                .pathParam("petId", petId)
                .post("/pet/{petId}/uploadImage");
    }

    public Response addPet(final PetDto petDto) {
        return getRequestSpecification()
                .body(petDto)
                .post("/pet");
    }

    public Response updatePet(final PetDto petDto) {
        return getRequestSpecification()
                .body(petDto)
                .put("/pet");
    }

    public Response findPetsByStatus(final String... status) {
        return getRequestSpecification()
                .queryParam("status", (Object[]) status)
                .get("/pet/findByStatus");
    }

    public Response getPetById(final long petId) {
        return getRequestSpecification()
                .pathParam("petId", petId)
                .get("/pet/{petId}");
    }

    public Response updatePetWithForm(final long petId, final String name, final String status) {
        return getRequestSpecification()
                .contentType(ContentType.URLENC)
                .formParam("name", name)
                .formParam("status", status)
                .pathParam("petId", petId)
                .post("/pet/{petId}");
    }

    public Response deletePet(final long petId, final String apiKey) {
        return getRequestSpecification()
                .header("api_key", apiKey)
                .pathParam("petId", petId)
                .delete("/pet/{petId}");
    }

}
