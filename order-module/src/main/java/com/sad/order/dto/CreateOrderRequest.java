package com.sad.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter@Setter
public class CreateOrderRequest {
    private Long customerId;
    private List<OrderItemRequest> orderItems;
}
