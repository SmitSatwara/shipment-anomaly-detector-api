package com.sad.shipment.entity;
import com.sad.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carriers")
@Getter
@Setter
public class Carrier extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String carrierCode;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
}
