package com.example.SpringFlowerShop.controller.rest;

import com.example.SpringFlowerShop.dto.OrderItemDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class OrderItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testGetAllOrderItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderItems"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetAllOrderItemsByOrderId() throws Exception {
        long orderId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderItems/order/" + orderId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetAllOrderItemsByProductId() throws Exception {
        int productId = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderItems/product/" + productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetOrderItemByOrderIdAndProductId() throws Exception {
        int orderId = 2;
        int productId = 1;
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orderItems/order/" + orderId + "/product/" + productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(orderItemDto.getId()));
    }

    @Test
    public void testCreateOrderItem() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orderItems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderId\":2,\"productId\":1,\"quantity\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateOrderItemByOrderIdAndProductId() throws Exception {
        int orderId = 2;
        int productId = 1;
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(1L);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/orderItems/order/" + orderId + "/product/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderId\":1,\"productId\":1,\"quantity\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(orderItemDto.getId()));
    }

    @Test
    public void testUpdateOrderItemByOrderIdAndProductId_NotFound() throws Exception {
        int orderId = 1;
        int productId = 1;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/orderItems/order/" + orderId + "/product/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderId\":1,\"productId\":1,\"quantity\":1}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteOrderItemByOrderIdAndProductId() throws Exception {
        int orderId = 2;
        int productId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/orderItems/order/" + orderId + "/product/" + productId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteOrderItemByOrderIdAndProductId_NotFound() throws Exception {
        int orderId = 1;
        int productId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/orderItems/order/" + orderId + "/product/" + productId))
                .andExpect(status().isNotFound());
    }
}
