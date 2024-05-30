package com.example.SpringFlowerShop.repository;

import com.example.SpringFlowerShop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Optional<Customer> findByEmail(String email);
}
