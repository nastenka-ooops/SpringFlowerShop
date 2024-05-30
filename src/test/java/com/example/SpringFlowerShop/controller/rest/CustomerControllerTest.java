package com.example.SpringFlowerShop.controller.rest;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllCustomers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCustomerById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllOrdersByCustomerId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/1/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                        .content("{\"id\":1,\"firstName\":\"anasty\",\"lastName\":\"shmasty\",\"address\":\"d\",\"phone\":\"2345\",\"email\":\"ertyu\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateCustomerById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/1")
                        .content("{\"id\":1,\"firstName\":\"anasty\",\"lastName\":\"shmasty\",\"address\":\"d\",\"phone\":\"f\",\"email\":\"s\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
