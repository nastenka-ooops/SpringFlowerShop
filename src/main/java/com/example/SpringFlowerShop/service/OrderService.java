package com.example.SpringFlowerShop.service;

import com.example.SpringFlowerShop.dto.InventoryDto;
import com.example.SpringFlowerShop.dto.OrderDto;
import com.example.SpringFlowerShop.dto.OrderItemDto;
import com.example.SpringFlowerShop.dto.OrderWithCustomerInfoDto;
import com.example.SpringFlowerShop.entity.*;
import com.example.SpringFlowerShop.mapping.InventoryMapper;
import com.example.SpringFlowerShop.mapping.OrderItemMapper;
import com.example.SpringFlowerShop.mapping.OrderMapper;
import com.example.SpringFlowerShop.mapping.OrderWithCustomerInfoMapper;
import com.example.SpringFlowerShop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper = new OrderMapper();
    private final InventoryService inventoryService;
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper = new InventoryMapper();
    private final OrderItemMapper orderItemMapper = new OrderItemMapper();
    private final OrderWithCustomerInfoMapper orderWithCustomerInfoMapper = new OrderWithCustomerInfoMapper();

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository, ProductRepository productRepository, InventoryService inventoryService, InventoryRepository orderInventoryRepository, InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.inventoryService = inventoryService;
        this.inventoryRepository = inventoryRepository;
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::mapToOrderDto).collect(Collectors.toList());
    }

    public Optional<OrderDto> getOrderById(Long id) {
        return orderRepository.findById(id).map(orderMapper::mapToOrderDto);
    }

    public OrderWithCustomerInfoDto getOrderByIdWithCustomerInfo(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory with product id " + id + " not found"));
        Customer customer = order.getCustomer();
        return orderWithCustomerInfoMapper
                .mapToInventoryWithProductInfoDto(order, customer);
    }

    public List<OrderItemDto> getOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return orderItems.stream()
                .map(OrderItemMapper::mapToOrderItemDto)
                .collect(Collectors.toList());
    }

    public OrderDto createOrder(String customerName, OrderDto orderDto) {
        Order order = new Order();

        Optional<Customer> customer = customerRepository.findByEmail(customerName);

        if (customer.isPresent()) {
            order.setCustomer(customer.get());
            order.setDate(Date.valueOf(LocalDate.now()));
            order.setStatus(Status.CHECKOUT);
        }

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto item : orderDto.getItems()) {
            if (item.getQuantity() > 0) {
                OrderItem orderItem = new OrderItem();
                Optional<Product> product = productRepository.findById(item.getProductId());

                if (product.isPresent()) {
                    orderItem.setOrder(savedOrder);
                    orderItem.setProduct(product.get());
                    orderItem.setQuantity(item.getQuantity());
                    orderItems.add(orderItem);
                    orderItemRepository.save(orderItem);

                    Optional<Inventory> inventory = inventoryRepository.findByProductId(product.get().getId());
                    if (inventory.isPresent()) {
                        inventory.get().setQuantity(inventory.get().getQuantity()-orderItem.getQuantity());
                        inventoryService.updateInventoryByProductId(inventory.get().getId(), inventoryMapper.mapToInventoryDto(inventory.get()));
                    }
                }
            }
        }
        savedOrder.setOrderItems(orderItems);
        return orderMapper.mapToOrderDto(savedOrder);
    }

    public Optional<OrderDto> updateOrderById(Long id, OrderDto orderDto) {
        return orderRepository.findById(id).map(order -> {
            order.setId(orderDto.getId());
            order.setDate(orderDto.getDate());
            order.setCustomer(customerRepository.findById(orderDto.getCustomerId()).get());
            order.setStatus(orderDto.getStatus());
            return orderMapper.mapToOrderDto(orderRepository.save(order));
        });
    }

    public boolean deleteOrderById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomer(customerRepository.findById(orderDto.getCustomerId()).get());
        order.setDate(orderDto.getDate());
        order.setStatus(orderDto.getStatus());
        order.setOrderItems(new ArrayList<>());
        Order savedOrder = orderRepository.save(order);
        return orderMapper.mapToOrderDto(savedOrder);
    }
}
