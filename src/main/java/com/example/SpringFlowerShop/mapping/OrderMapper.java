package com.example.SpringFlowerShop.mapping;

import com.example.SpringFlowerShop.dto.CustomerDto;
import com.example.SpringFlowerShop.dto.OrderDto;
import com.example.SpringFlowerShop.entity.Customer;
import com.example.SpringFlowerShop.entity.Order;

public class OrderMapper {
    public OrderDto mapToOrderDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setCustomerId(order.getCustomerId());
        dto.setStatus(order.getStatus());
        return dto;
    }
    public Order mapToOrderEntity (OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setDate(order.getDate());
        order.setCustomerId(order.getCustomerId());
        order.setStatus(order.getStatus());
        return order;
    }
}
