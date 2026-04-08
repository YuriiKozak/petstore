package io.swagger.petstore.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.swagger.petstore.controller.PetController;
import io.swagger.petstore.controller.StoreController;
import io.swagger.petstore.controller.UserController;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    protected static final PetController petController = new PetController();
    protected static final StoreController storeController = new StoreController();
    protected static final UserController userController = new UserController();

    @BeforeAll
    static void setup() {
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory(
                        (_, _) -> new ObjectMapper()
                                .registerModule(new JavaTimeModule())
                                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)));
    }

}
