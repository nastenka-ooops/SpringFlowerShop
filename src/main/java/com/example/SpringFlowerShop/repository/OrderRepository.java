package com.example.SpringFlowerShop.repository;

import com.example.SpringFlowerShop.entity.Customer;
import com.example.SpringFlowerShop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
}
