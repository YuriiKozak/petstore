package io.swagger.petstore.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.swagger.petstore.controller.PetController;
import io.swagger.petstore.controller.StoreController;
import io.swagger.petstore.controller.UserController;
import io.swagger.petstore.dto.TokenDto;
import io.swagger.petstore.dto.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static io.swagger.petstore.utils.TestDataGenerator.generateRandomUser;

public abstract class BaseTest {

    protected static PetController petController;
    protected static StoreController storeController;
    protected static UserController userController;

    @BeforeAll
    static void setup() {
        RestAssured.config = config()
                .objectMapperConfig(objectMapperConfig()
                        .jackson2ObjectMapperFactory((_, _) ->
                                new ObjectMapper()
                                        .registerModule(new JavaTimeModule())
                                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)));
    }

    @BeforeEach
    void setupTest() {
        final UserDto userRequest = generateRandomUser();

        new UserController()
                .createUser(userRequest)
                .then()
                .assertThat()
                .statusCode(200);

        final TokenDto tokenDto = new UserController()
                .loginUser(userRequest.getUsername(), userRequest.getPassword())
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(TokenDto.class);

        final String token = tokenDto.getMessage().split(":")[1];

        petController = new PetController().withToken(token);
        storeController = new StoreController().withToken(token);
        userController = new UserController().withToken(token);
    }

}
