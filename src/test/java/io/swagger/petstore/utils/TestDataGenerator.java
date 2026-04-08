package io.swagger.petstore.utils;

import com.github.javafaker.Faker;
import io.swagger.petstore.dto.OrderDto;
import io.swagger.petstore.dto.PetDto;
import io.swagger.petstore.dto.UserDto;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TestDataGenerator {

    private static final Faker faker = new Faker();

    public static OrderDto generateRandomOrder() {
        return new OrderDto()
                .setId(faker.number().randomNumber())
                .setPetId(faker.number().randomNumber())
                .setQuantity(faker.number().numberBetween(1, 10))
                .setShipDate(OffsetDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.MILLIS))
                .setStatus(OrderDto.Status.placed)
                .setComplete(false);
    }

    public static PetDto generateRandomPet() {
        return new PetDto()
                .setId(faker.number().randomNumber())
                .setName(faker.animal().name())
                .setPhotoUrls(List.of(faker.internet().url()))
                .setStatus(PetDto.Status.available);
    }

    public static UserDto generateRandomUser() {
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
