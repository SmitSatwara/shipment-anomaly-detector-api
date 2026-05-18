package com.sad.shipment.entity;
import com.sad.order.entity.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.sad.common.entity.*;

import java.time.LocalDate;

@Entity
@Table(name = "shipments")
@Getter@Setter
public class Shipment extends com.sad.common.entity.BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "carrier_id",nullable = false)
    private Carrier carrier;
    @Column(nullable = false,unique = true)
    private String trackingNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus shipmentStatus;
    @ManyToOne
    @JoinColumn(name = "origin_address_id")
    private Address origin;

    @ManyToOne
    @JoinColumn(name = "destination_address_id")
    private Address destination;
    @Column(nullable = false)
    private LocalDate expectedDeliveryDate;
    @Column
    private LocalDate actualDeliveryDate;

}
