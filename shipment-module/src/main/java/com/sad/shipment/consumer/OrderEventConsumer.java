package com.sad.shipment.consumer;

import com.sad.order.event.OrderCreatedEvent;
import com.sad.shipment.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventConsumer {
    private final ShipmentService shipmentService;

    @KafkaListener(
            topics = "order.created",
            groupId = "shipment-service-group"
    )
    public void consumeOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        shipmentService.createShipment(orderCreatedEvent);
    }
}
