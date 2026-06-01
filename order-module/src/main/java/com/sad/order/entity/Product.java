package com.sad.order.entity;

import com.sad.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column
    private String description;
    @Column(nullable = false)
    private BigDecimal weight;
    @Column(nullable = false)
    private BigDecimal length;
    @Column(nullable = false)
    private BigDecimal width;
    @Column(nullable = false)
    private BigDecimal height;
    @Column(nullable = false)
    private BigDecimal unitPrice;

}
