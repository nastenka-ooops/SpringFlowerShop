package com.example.SpringFlowerShop.mapping;

import com.example.SpringFlowerShop.dto.OrderItemDto;
import com.example.SpringFlowerShop.entity.OrderItem;

public class OrderItemMapper {
    public OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(orderItem.getId());
        dto.setOrderId(orderItem.getOrderId());
        dto.setProductId(orderItem.getProductId());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }

    public OrderItem mapToOrderItemEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setOrderId(orderItemDto.getOrderId());
        orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }
}
