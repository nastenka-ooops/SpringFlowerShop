package com.example.SpringFlowerShop.repository;

import com.example.SpringFlowerShop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);

    List<OrderItem> findByProductId(int productId);

    Optional<OrderItem> findByOrderIdAndProductId(int orderId, int productId);

    boolean existsByOrderIdAndProductId(int orderId, int productId);

    void deleteByOrderIdAndProductId(int orderId, int productId);

}
