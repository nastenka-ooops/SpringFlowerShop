package com.example.SpringFlowerShop.mapping;

import com.example.SpringFlowerShop.dto.OrderWithCustomerInfoDto;
import com.example.SpringFlowerShop.entity.Customer;
import com.example.SpringFlowerShop.entity.Order;

public class OrderWithCustomerInfoMapper {
    public OrderWithCustomerInfoDto mapToInventoryWithProductInfoDto(Order order, Customer customer) {
        OrderWithCustomerInfoDto dto = new OrderWithCustomerInfoDto();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setStatus(order.getStatus());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setAddress(customer.getAddress());
        dto.setPhone(customer.getPhone());
        dto.setEmail(customer.getEmail());
        return dto;
    }
}
