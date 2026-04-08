package io.swagger.petstore.tests;

import io.swagger.petstore.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.swagger.petstore.utils.TestDataGenerator.generateRandomUser;
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

}
