package com.sad.order.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class OrderCreatedEvent {
    private final Long orderId;
    private final String orderReferenceNumber;
    private final LocalDateTime occurredAt;
}
