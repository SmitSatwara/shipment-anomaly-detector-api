package com.sad.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter@Setter
public class OrderItemResponse {
    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private Long supplierId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
