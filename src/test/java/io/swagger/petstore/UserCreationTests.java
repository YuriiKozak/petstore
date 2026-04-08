package io.swagger.petstore;

import com.github.javafaker.Faker;
import io.swagger.petstore.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserCreationTests extends BaseTest {

    @Test
    @DisplayName("Create user test")
    public void createUserTest() {
        final UserDto userRequest = generateRandomUser();

        userController
                .createUser(userRequest)
                .then()
                .assertThat()
                .statusCode(200);

        final UserDto userResponse = userController
                .getUserByName(userRequest.getUsername())
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(UserDto.class);

        assertThat(userResponse).isEqualTo(userRequest);

        final String session = userController
                .loginUser(userRequest.getUsername(), userRequest.getPassword())
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .asString();

        System.out.println(session);
    }

    private UserDto generateRandomUser() {
        final Faker faker = new Faker();

        return new UserDto()
                .setId(faker.number().randomNumber())
                .setUsername(faker.name().username())
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password())
                .setPhone(faker.phoneNumber().cellPhone())
                .setUserStatus(faker.number().numberBetween(0, 1));
    }

}
