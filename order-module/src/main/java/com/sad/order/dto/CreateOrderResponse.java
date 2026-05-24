package com.sad.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class CreateOrderResponse {
    private Long orderId;
    private String orderReferenceNumber;
    private String orderStatus;
    private List<OrderItemResponse> orderItems;
}
