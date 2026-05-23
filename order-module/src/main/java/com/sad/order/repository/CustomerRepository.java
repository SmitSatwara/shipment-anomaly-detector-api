package com.sad.order.repository;

import com.sad.order.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long > {
}
