package com.sad.shipment.service.impl;

import com.sad.common.entity.AddressEntityType;
import com.sad.order.entity.Order;
import com.sad.order.event.OrderCreatedEvent;
import com.sad.order.repository.OrderRepository;
import com.sad.shipment.entity.Shipment;
import com.sad.shipment.entity.ShipmentStatus;
import com.sad.shipment.repository.CarrierRepository;
import com.sad.shipment.repository.ShipmentRepository;
import com.sad.shipment.service.ShipmentService;
import com.sad.common.exception.ResourceNotFoundException;
import com.sad.common.entity.Address;
import com.sad.common.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;
    private final CarrierRepository carrierRepository;
    private final AddressRepository addressRepository;


    @Override
    public void createShipment(OrderCreatedEvent orderCreatedEvent) {
        Order order = orderRepository.findById(orderCreatedEvent.getOrderId())
                .orElseThrow(()->new ResourceNotFoundException("Order not found :"+orderCreatedEvent.getOrderId()));

        Address destinationAddress = addressRepository.findByEntityIdAndEntityTypeAndIsDefaultTrue(order.getCustomer().getId(), AddressEntityType.CUSTOMER)
                .orElseThrow(()->new ResourceNotFoundException("Address not found :"+orderCreatedEvent.getOrderId()));

        Shipment shipment = new Shipment();
        shipment.setOrder(order);
        shipment.setShipmentStatus(ShipmentStatus.CREATED);
        shipment.setDestination(destinationAddress);
        shipment.setExpectedDeliveryDate(LocalDate.now());
        shipment.setTrackingNumber(generateTrackingNumber());
        shipmentRepository.save(shipment);

    }


    private String generateTrackingNumber() {
        return "TRCK-"+ LocalDate.now() +"-"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }
}
