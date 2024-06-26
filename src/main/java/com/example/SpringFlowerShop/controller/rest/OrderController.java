package com.example.SpringFlowerShop.controller.rest;

import com.example.SpringFlowerShop.dto.OrderDto;
import com.example.SpringFlowerShop.dto.OrderItemDto;
import com.example.SpringFlowerShop.dto.OrderWithCustomerInfoDto;
import com.example.SpringFlowerShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        Optional<OrderDto> orderDto = orderService.getOrderById(id);
        return orderDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/customerInfo")
    public ResponseEntity<OrderWithCustomerInfoDto> getOrderByIdWithCustomerInfo(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderByIdWithCustomerInfo(id));
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<OrderItemDto>> getOrderItemsByOrderId(@PathVariable Long id) {
        List<OrderItemDto> orderItemDtos = orderService.getOrderItemsByOrderId(id);
        if (orderItemDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(orderItemDtos);
        }
    }

    @PostMapping("")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrderById(@PathVariable Long id,
                                                    @RequestBody OrderDto orderDto) {
        return orderService.updateOrderById(id, orderDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.deleteOrderById(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
