package com.example.SpringFlowerShop.service;

import com.example.SpringFlowerShop.dto.CustomerDto;
import com.example.SpringFlowerShop.dto.OrderDto;
import com.example.SpringFlowerShop.entity.Customer;
import com.example.SpringFlowerShop.mapping.CustomerMapper;
import com.example.SpringFlowerShop.mapping.OrderMapper;
import com.example.SpringFlowerShop.repository.CustomerRepository;
import com.example.SpringFlowerShop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final CustomerMapper customerMapper = new CustomerMapper();
    private final OrderMapper orderMapper = new OrderMapper();

    @Autowired
    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::mapToCustomerDto).collect(Collectors.toList());
    }

    public Optional<CustomerDto> getCustomerById(Long id) {
        return customerRepository.findById(id).map(customerMapper::mapToCustomerDto);
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.mapToCustomerEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.mapToCustomerDto(savedCustomer);
    }

    public Optional<CustomerDto> updateCustomerById(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id).map(customer -> {
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setAddress(customerDto.getAddress());
            customer.setPhone(customerDto.getPhone());
            customer.setEmail(customerDto.getEmail());
            return customerMapper.mapToCustomerDto(customerRepository.save(customer));
        });
    }

    public boolean deleteCustomerById(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<OrderDto> getAllOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(orderMapper::mapToOrderDto).collect(Collectors.toList());
    }

}
