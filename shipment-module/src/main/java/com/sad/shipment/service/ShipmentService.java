package com.sad.shipment.service;
import com.sad.order.event.OrderCreatedEvent;
public interface ShipmentService {
    void createShipment(OrderCreatedEvent orderCreatedEvent);
}
