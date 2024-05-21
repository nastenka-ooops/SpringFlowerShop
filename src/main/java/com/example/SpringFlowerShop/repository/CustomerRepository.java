package com.example.SpringFlowerShop.repository;

import com.example.SpringFlowerShop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
