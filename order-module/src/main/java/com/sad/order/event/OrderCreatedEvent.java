package com.sad.order.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@RequiredArgsConstructor
public class OrderCreatedEvent {
    private final Long orderId;
    private final String orderReferenceNumber;
    private final LocalDateTime occurredAt;
}
