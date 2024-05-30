package com.example.SpringFlowerShop.mapping;

import com.example.SpringFlowerShop.dto.OrderItemDto;
import com.example.SpringFlowerShop.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(orderItem.getId());
        dto.setOrderId(orderItem.getOrder().getId());
        dto.setProductId(orderItem.getProduct().getId());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }

    public static OrderItem mapToOrderItemEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        //orderItem.setOrder(orderItemDto.getOrder());
        //orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }
}
