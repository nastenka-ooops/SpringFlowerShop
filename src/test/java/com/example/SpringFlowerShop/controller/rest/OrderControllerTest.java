package com.example.SpringFlowerShop.controller.rest;

import com.example.SpringFlowerShop.dto.OrderItemDto;
import com.example.SpringFlowerShop.dto.OrderWithCustomerInfoDto;
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
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetAllOrders() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetOrderById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetOrderById_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/1234"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\":\"2024-05-11\",\"customerId\":1,\"status\":\"CHECKOUT\",\"items\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateOrderById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"date\":\"2024-05-11\",\"customerId\":1,\"status\":\"CHECKOUT\",\"items\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateOrderById_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/orders/1234")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"customerName\":\"John Doe\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/orders/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteOrder_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/orders/1234"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetOrderByIdWithCustomerInfo() throws Exception {
        OrderWithCustomerInfoDto orderWithCustomerInfoDto = new OrderWithCustomerInfoDto();
        orderWithCustomerInfoDto.setId(1L);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/1/customerInfo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(orderWithCustomerInfoDto.getId()));
    }

    @Test
    public void testGetOrderItemsByOrderId() throws Exception {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(10L);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/10/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetOrderItemsByOrderId_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/1/items"))
                .andExpect(status().isNotFound());
    }

}
