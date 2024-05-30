package com.example.SpringFlowerShop.mapping;

import com.example.SpringFlowerShop.dto.OrderDto;
import com.example.SpringFlowerShop.entity.Order;

public class OrderMapper {
    public OrderDto mapToOrderDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setStatus(order.getStatus());

        dto.setItems(order.getOrderItems().stream().map
                (OrderItemMapper::mapToOrderItemDto).toList());

        return dto;
    }

}
