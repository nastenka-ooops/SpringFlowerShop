package com.example.SpringFlowerShop.controller;

import com.example.SpringFlowerShop.dto.ProductDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("products"));
    }

    @Test
    public void testUserList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("users"));
    }

    @Test
    public void testShowAddProductForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-product"));
    }

    @Test
    public void testShowEditProductForm() throws Exception {
        Long productId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/products/edit/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("edit-product"));
    }
    @Test
    public void testDeleteUser() throws Exception {
        Long userId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/users")
                        .param("userId", userId.toString())
                        .param("action", "delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/users"));
    }

    @Test
    public void testAddProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products/add")
                        .flashAttr("product", productDto))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"));
    }

    @Test
    public void testEditProduct() throws Exception {
        Long productId = 1L;
        ProductDto productDto = new ProductDto();
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products/edit/{id}", productId)
                        .flashAttr("product", productDto))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Long productId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products/delete/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"));
    }
}
