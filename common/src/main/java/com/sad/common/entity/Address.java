package com.sad.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressEntityType entityType;
    @Column(nullable = false)
    private Long entityId;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private Boolean isDefault;

}
