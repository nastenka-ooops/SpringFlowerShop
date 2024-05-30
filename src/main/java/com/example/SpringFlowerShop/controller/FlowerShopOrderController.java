package com.example.SpringFlowerShop.controller;

import com.example.SpringFlowerShop.dto.OrderDto;
import com.example.SpringFlowerShop.dto.ProductDto;
import com.example.SpringFlowerShop.service.OrderService;
import com.example.SpringFlowerShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FlowerShopOrderController {
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public FlowerShopOrderController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String showOrderPage(Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "order";
    }

    @PostMapping("/order")
    public String placeOrder(OrderDto orderDto, RedirectAttributes redirectAttributes) {
        orderService.createOrder(getCustomerName(), orderDto);
        redirectAttributes.addFlashAttribute("order", orderDto);
        return "redirect:/order/success";
    }

    static String getCustomerName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/order/success")
    public String showSuccessPage() {
        return "order-success";
    }
}
