package com.sad.shipment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.sad.common.entity.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tracking_events")
@Getter @Setter
public class TrackingEvent  extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "shipment_id",nullable = false)
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "carrier_id",nullable = false)
    private Carrier carrier;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private LocalDateTime occurredAt;
}
