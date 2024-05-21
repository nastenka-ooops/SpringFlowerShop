package com.example.SpringFlowerShop.service;

import com.example.SpringFlowerShop.dto.CustomerDto;
import com.example.SpringFlowerShop.dto.InventoryWithProductInfoDto;
import com.example.SpringFlowerShop.dto.OrderDto;
import com.example.SpringFlowerShop.dto.OrderWithCustomerInfoDto;
import com.example.SpringFlowerShop.entity.Customer;
import com.example.SpringFlowerShop.entity.Inventory;
import com.example.SpringFlowerShop.entity.Order;
import com.example.SpringFlowerShop.entity.Product;
import com.example.SpringFlowerShop.mapping.OrderMapper;
import com.example.SpringFlowerShop.mapping.OrderWithCustomerInfoMapper;
import com.example.SpringFlowerShop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = new OrderMapper();
    private final OrderWithCustomerInfoMapper orderWithCustomerInfoMapper = new OrderWithCustomerInfoMapper();
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::mapToOrderDto).collect(Collectors.toList());
    }

    public Optional<OrderDto> getOrderById(Long id) {
        return orderRepository.findById(id).map(orderMapper::mapToOrderDto);
    }

    public OrderWithCustomerInfoDto getOrderByIdWithCustomerInfo(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory with product id " + id + " not found"));
        Customer customer = order.getCustomer();
        return orderWithCustomerInfoMapper
                .mapToInventoryWithProductInfoDto(order,customer);
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.mapToOrderEntity(orderDto);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.mapToOrderDto(savedOrder);
    }

    public Optional<OrderDto> updateOrderById(Long id, OrderDto orderDto){
        return orderRepository.findById(id).map(order -> {
            order.setId(orderDto.getId());
            order.setDate(orderDto.getDate());
            order.setCustomerId(orderDto.getCustomerId());
            order.setStatus(orderDto.getStatus());
            return orderMapper.mapToOrderDto(orderRepository.save(order));
        });
    }

    public boolean deleteOrderById(Long id) {
        if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
