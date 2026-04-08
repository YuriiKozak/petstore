package io.swagger.petstore.tests;

import io.swagger.petstore.dto.OrderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.swagger.petstore.utils.TestDataGenerator.generateRandomOrder;
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

}
