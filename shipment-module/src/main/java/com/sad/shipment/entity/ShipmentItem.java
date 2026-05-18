package com.sad.shipment.entity;
import com.sad.common.entity.*;
import com.sad.order.entity.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shipment_items")
@Getter
@Setter
public class ShipmentItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "order_item_id",nullable = false)
    private OrderItem orderItem;
    @Column(nullable = false)
    private Integer quantity;
}
