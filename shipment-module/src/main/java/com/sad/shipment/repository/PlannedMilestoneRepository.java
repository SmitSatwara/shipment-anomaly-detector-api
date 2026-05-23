package com.sad.shipment.repository;

import com.sad.shipment.entity.ShipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannedMilestoneRepository extends JpaRepository<ShipmentItem, Long> {
}
