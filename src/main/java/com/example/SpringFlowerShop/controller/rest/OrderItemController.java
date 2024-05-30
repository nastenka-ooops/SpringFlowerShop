package com.example.SpringFlowerShop.controller.rest;

import com.example.SpringFlowerShop.dto.OrderItemDto;
import com.example.SpringFlowerShop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/orderItems")
    public List<OrderItemDto> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/orderItems/order/{orderId}")
    public List<OrderItemDto> getAllOrderItemsByOrderId(@PathVariable Long orderId) {
        return orderItemService.getAllOrderItemsByOrderId(orderId);
    }

    @GetMapping("/orderItems/product/{productId}")
    public List<OrderItemDto> getAllOrderItemsByProductId(@PathVariable int productId) {
        return orderItemService.getAllOrderItemsByProductId(productId);
    }

    @GetMapping("/orderItems/order/{orderId}/product/{productId}")
    public ResponseEntity<OrderItemDto> getOrderItemByOrderIdAndProductId(@PathVariable int orderId,
                                                                          @PathVariable int productId) {
        Optional<OrderItemDto> orderItemDto = orderItemService.getOrderItemByOrderIdAndProductId(orderId, productId);
        return orderItemDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/orderItems")
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        return ResponseEntity.ok(orderItemService.createOrderItem(orderItemDto));
    }

    @PutMapping("/orderItems/order/{orderId}/product/{productId}")
    public ResponseEntity<OrderItemDto> updateOrderItemByOrderIdAndProductId(@PathVariable int orderId, @PathVariable int productId,
                                                                             @RequestBody OrderItemDto orderItemDto) {
        return orderItemService.updateOrderItemByOrderIdAndProductId(orderId, productId, orderItemDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/orderItems/order/{orderId}/product/{productId}")
    public ResponseEntity<Void> deleteOrderItemByOrderIdAndProductId(@PathVariable int orderId, @PathVariable int productId) {
        if (orderItemService.deleteOrderItemByOrderIdAndProductId(orderId, productId))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }

}
