package com.example.SpringFlowerShop.controller;

import com.example.SpringFlowerShop.dto.CustomerDto;
import com.example.SpringFlowerShop.dto.OrderDto;
import com.example.SpringFlowerShop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        Optional<CustomerDto> customerDto = customerService.getCustomerById(id);
        return customerDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/customers/{id}/orders")
    public ResponseEntity<List<OrderDto>> getAllOrdersByCustomerId(@PathVariable Long id) {
        Optional<CustomerDto> customerDto = customerService.getCustomerById(id);
        if (customerDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<OrderDto> orders = customerService.getAllOrdersByCustomerId(customerDto.get().getId());
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomerById(@PathVariable Long id,
                                                          @RequestBody CustomerDto customerDto) {
       return customerService.updateCustomerById(id, customerDto)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerService.deleteCustomerById(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
