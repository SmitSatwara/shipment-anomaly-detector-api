package com.sad.order.service;
import     com.sad.order.dto.*;
public interface OrderService {
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
