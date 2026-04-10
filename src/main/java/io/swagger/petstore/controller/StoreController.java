package io.swagger.petstore.controller;

import io.restassured.response.Response;
import io.swagger.petstore.dto.OrderDto;

public class StoreController extends BaseController<StoreController> {

    public Response getInventory() {
        return getRequestSpecification()
                .get("/store/inventory");
    }

    public Response placeOrder(final OrderDto orderDto) {
        return getRequestSpecification()
                .body(orderDto)
                .post("/store/order");
    }

    public Response getOrderById(final long orderId) {
        return getRequestSpecification()
                .pathParam("orderId", orderId)
                .get("/store/order/{orderId}");
    }

    public Response deleteOrder(final long orderId) {
        return getRequestSpecification()
                .pathParam("orderId", orderId)
                .delete("/store/order/{orderId}");
    }

}
