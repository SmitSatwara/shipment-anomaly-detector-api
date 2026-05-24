package com.sad.order.service.impl;
import com.sad.order.dto.*;
import com.sad.order.repository.CustomerRepository;
import com.sad.order.repository.OrderItemRepository;
import com.sad.order.repository.OrderRepository;
import com.sad.common.exception.*;
import com.sad.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;


    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {

        //Validate customer
        if(!customerRepository.existsById(createOrderRequest.getCustomerId())) {
            throw new ResourceNotFoundException("Customer not found"+createOrderRequest.getCustomerId());
        }


        return null;
    }

    private String generateOrderReferenceNumber(){
        return "ORD-"+ LocalDate.now() +"-"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }
}
