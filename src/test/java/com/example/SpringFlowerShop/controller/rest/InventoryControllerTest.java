package com.example.SpringFlowerShop.controller.rest;

import com.example.SpringFlowerShop.service.InventoryService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class InventoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateInventory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/inventories")
                        .content("{\"productId\":8,\"quantity\":3,\"shipmentDate\":\"2024-05-06\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllInventories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInventoryByProductId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInventoryWithProductInfoByProductId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventories/1/productInfo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateInventoryByProductId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/inventories/1")
                        .content("{\"productId\":1,\"quantity\":3,\"shipmentDate\":\"2024-05-06\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteInventoryByProductId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/inventories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
