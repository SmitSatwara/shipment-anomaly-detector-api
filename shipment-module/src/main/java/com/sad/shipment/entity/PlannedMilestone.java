package com.sad.shipment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.sad.common.entity.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "planned_milestones")
@Getter
@Setter
public class PlannedMilestone extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "shipment_id",nullable = false)
    private Shipment shipment;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;
    @Column(nullable = false)
    private LocalDateTime expectedAt;

}
