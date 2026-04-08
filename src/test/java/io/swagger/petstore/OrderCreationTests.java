package io.swagger.petstore;

import com.github.javafaker.Faker;
import io.swagger.petstore.dto.OrderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderCreationTests extends BaseTest {

    @Test
    @DisplayName("Create order test")
    public void createOrderTest() {
        final OrderDto orderRequest = generateRandomOrder();

        storeController
                .placeOrder(orderRequest)
                .then()
                .assertThat()
                .statusCode(200);

        final OrderDto orderResponse = storeController
                .getOrderById(orderRequest.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(OrderDto.class);

        assertThat(orderRequest).isEqualTo(orderResponse);
    }

    private OrderDto generateRandomOrder() {
        final Faker faker = new Faker();

        return new OrderDto()
                .setId(faker.number().randomNumber())
                .setPetId(faker.number().randomNumber())
                .setQuantity(faker.number().numberBetween(1, 10))
                .setShipDate(OffsetDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.MILLIS))
                .setStatus(OrderDto.Status.placed)
                .setComplete(false);
    }

}
