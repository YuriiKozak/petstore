package io.swagger.petstore.tests;

import io.swagger.petstore.dto.PetDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.swagger.petstore.utils.TestDataGenerator.generateRandomPet;
import static org.assertj.core.api.Assertions.assertThat;

public class PetCreationTests extends BaseTest {

    @Test
    @DisplayName("Create pet test")
    public void createPetTest() {
        final PetDto petRequest = generateRandomPet();

        petController
                .addPet(petRequest)
                .then()
                .assertThat()
                .statusCode(200);

        final PetDto petResponse = petController
                .getPetById(petRequest.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(PetDto.class);

        assertThat(petResponse).isEqualTo(petRequest);
    }

    @Test
    @DisplayName("Upload image test")
    public void uploadImageTest() {
        final PetDto petRequest = generateRandomPet();

        petController
                .addPet(petRequest)
                .then()
                .assertThat()
                .statusCode(200);

        final File imageFile = new File("src/test/resources/images/cat.png");

        petController
                .uploadFile(petRequest.getId(), "test", imageFile)
                .then()
                .assertThat()
                .statusCode(200);
    }

}
