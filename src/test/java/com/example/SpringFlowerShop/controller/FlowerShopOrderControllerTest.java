package com.example.SpringFlowerShop.controller;

import com.example.SpringFlowerShop.dto.OrderDto;
import com.example.SpringFlowerShop.dto.ProductDto;
import com.example.SpringFlowerShop.service.OrderService;
import com.example.SpringFlowerShop.service.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class FlowerShopOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderService orderService;

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    public void testShowOrderPage() throws Exception {
        List<ProductDto> products = productService.getAllProducts();

        mockMvc.perform(MockMvcRequestBuilders.get("/order"))
                .andExpect(status().isOk())
                .andExpect(view().name("order"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", products));
    }

    @Test
    void testPlaceOrder() throws Exception {
        OrderDto orderDto = new OrderDto();

        mockMvc.perform(post("/order")
                .flashAttr("orderDto", orderDto))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    public void testShowSuccessPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order/success"))
                .andExpect(status().isOk())
                .andExpect(view().name("order-success"));
    }
}
