package com.example.SpringFlowerShop.mapping;

import com.example.SpringFlowerShop.dto.CustomerDto;
import com.example.SpringFlowerShop.dto.InventoryDto;
import com.example.SpringFlowerShop.entity.Customer;
import com.example.SpringFlowerShop.entity.Inventory;

public class CustomerMapper {
    public CustomerDto mapToCustomerDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setAddress(customer.getAddress());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        return dto;
    }
    public Customer mapToCustomerEntity (CustomerDto dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setAddress(customer.getAddress());
        customer.setPhone(customer.getPhone());
        customer.setEmail(customer.getEmail());
        return customer;
    }
}
