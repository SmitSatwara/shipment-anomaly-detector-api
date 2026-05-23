package com.sad.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sad.order.entity.Product;
public interface ProductRepository extends JpaRepository<Product,Long> {
}
