package com.sad.incident.entity;

import jakarta.persistence.*;
import com.sad.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_users")
@Getter
@Setter
public class AppUser extends BaseEntity {
    @Column(nullable = false)
    private  String name;
    @Column(nullable = false,unique = true)
    private String employeeId;
    @Column(nullable = false,unique = true)
    private  String email;
    @Column(nullable = false,length = 15)
    private  String phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  AppUserRole role;
}
