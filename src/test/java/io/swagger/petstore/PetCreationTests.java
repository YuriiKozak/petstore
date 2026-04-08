package io.swagger.petstore;

import com.github.javafaker.Faker;
import io.swagger.petstore.dto.PetDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    private PetDto generateRandomPet() {
        final Faker faker = new Faker();

        return new PetDto()
                .setId(faker.number().randomNumber())
                .setName(faker.animal().name())
                .setPhotoUrls(List.of(faker.internet().url()))
                .setStatus(PetDto.Status.available);
    }

}
