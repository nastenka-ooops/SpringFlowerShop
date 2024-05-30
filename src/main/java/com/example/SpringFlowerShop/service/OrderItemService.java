package com.example.SpringFlowerShop.service;

import com.example.SpringFlowerShop.dto.OrderItemDto;
import com.example.SpringFlowerShop.entity.OrderItem;
import com.example.SpringFlowerShop.mapping.OrderItemMapper;
import com.example.SpringFlowerShop.repository.OrderItemRepository;
import com.example.SpringFlowerShop.repository.OrderRepository;
import com.example.SpringFlowerShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemMapper orderItemMapper = new OrderItemMapper();

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream()
                .map(OrderItemMapper::mapToOrderItemDto)
                .collect(Collectors.toList());
    }

    public List<OrderItemDto> getAllOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return orderItems.stream()
                .map(OrderItemMapper::mapToOrderItemDto)
                .collect(Collectors.toList());
    }

    public List<OrderItemDto> getAllOrderItemsByProductId(int productId) {
        List<OrderItem> orderItems = orderItemRepository.findByProductId(productId);
        return orderItems.stream()
                .map(OrderItemMapper::mapToOrderItemDto)
                .collect(Collectors.toList());
    }

    public Optional<OrderItemDto> getOrderItemByOrderIdAndProductId(int orderId, int productId) {
        return orderItemRepository.findByOrderIdAndProductId(orderId, productId)
                .map(OrderItemMapper::mapToOrderItemDto);
    }

    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(orderRepository.findById(orderItemDto.getOrderId()).get());
        orderItem.setProduct(productRepository.findById(orderItemDto.getProductId()).get());
        orderItem.setQuantity(orderItem.getQuantity());

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.mapToOrderItemDto(savedOrderItem);
    }

    public Optional<OrderItemDto> updateOrderItemByOrderIdAndProductId(int orderId, int productId, OrderItemDto orderItemDto) {
        return orderItemRepository.findByOrderIdAndProductId(orderId, productId).map(orderItem -> {
            orderItem.setQuantity(orderItemDto.getQuantity());
            OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
            return orderItemMapper.mapToOrderItemDto(updatedOrderItem);
        });
    }

    public boolean deleteOrderItemByOrderIdAndProductId(int orderId, int productId) {
        if (orderItemRepository.existsByOrderIdAndProductId(orderId, productId)) {
            orderItemRepository.deleteByOrderIdAndProductId(orderId, productId);
            return true;
        } else {
            return false;
        }
    }
}
