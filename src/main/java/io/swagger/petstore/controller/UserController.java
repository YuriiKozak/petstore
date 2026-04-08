package io.swagger.petstore.controller;

import io.restassured.response.Response;
import io.swagger.petstore.dto.UserDto;

import java.util.List;

public class UserController extends BaseController {

    public Response createUser(final UserDto userDto) {
        return getRequestSpecification()
                .body(userDto)
                .post("/user");
    }

    public Response createUsersWithArray(final List<UserDto> users) {
        return getRequestSpecification()
                .body(users)
                .post("/user/createWithArray");
    }

    public Response createUsersWithList(final List<UserDto> users) {
        return getRequestSpecification()
                .body(users)
                .post("/user/createWithList");
    }

    public Response loginUser(final String username, final String password) {
        return getRequestSpecification()
                .queryParam("username", username)
                .queryParam("password", password)
                .get("/user/login");
    }

    public Response logoutUser() {
        return getRequestSpecification()
                .get("/user/logout");
    }

    public Response getUserByName(final String username) {
        return getRequestSpecification()
                .pathParam("username", username)
                .get("/user/{username}");
    }

    public Response updateUser(final String username, final UserDto userDto) {
        return getRequestSpecification()
                .body(userDto)
                .pathParam("username", username)
                .put("/user/{username}");
    }

    public Response deleteUser(final String username) {
        return getRequestSpecification()
                .pathParam("username", username)
                .delete("/user/{username}");
    }

}
