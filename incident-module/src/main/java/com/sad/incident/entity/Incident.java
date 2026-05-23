package com.sad.incident.entity;
import com.sad.common.entity.*;
import com.sad.shipment.entity.PlannedMilestone;
import com.sad.shipment.entity.Shipment;
import com.sad.shipment.entity.TrackingEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "incidents")
@Getter @Setter
public class Incident extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "planned_milestone_id", nullable = false )
    private PlannedMilestone plannedMilestone;

    @ManyToOne
    @JoinColumn(name = "tracking_event_id")
    private TrackingEvent trackingEvent;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentSeverity severity;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentStatus status;
    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private AppUser assignedUser;
    @Column
    private String resolutionNotes;
    @Column(nullable = false)
    private LocalDateTime occurredAt;
    @Column
    private LocalDateTime resolvedAt;
}
