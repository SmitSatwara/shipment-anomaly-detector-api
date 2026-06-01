package com.sad.order.publisher;
import com.sad.order.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventPublisher {
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private static final String ORDER_CREATED_TOPIC = "order.created";

    public void publishOrderCreatedEvent(OrderCreatedEvent event){
        kafkaTemplate.send(ORDER_CREATED_TOPIC, event.getOrderId().toString(), event);
    }
}
