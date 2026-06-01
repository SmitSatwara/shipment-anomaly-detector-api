package com.sad.order.controller;

import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sad.order.dto.CreateOrderRequest;
import com.sad.order.dto.CreateOrderResponse;
import com.sad.order.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        CreateOrderResponse response= orderService.createOrder(createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
