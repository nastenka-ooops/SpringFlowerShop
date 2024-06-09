package com.example.SpringFlowerShop.controller;

import com.example.SpringFlowerShop.dto.CustomerDto;
import com.example.SpringFlowerShop.dto.OrderDto;
import com.example.SpringFlowerShop.dto.OrderItemDto;
import com.example.SpringFlowerShop.dto.ProductDto;
import com.example.SpringFlowerShop.service.CustomerService;
import com.example.SpringFlowerShop.service.OrderService;
import com.example.SpringFlowerShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FlowerShopOrderController {
    private final ProductService productService;
    private final OrderService orderService;
    private final CustomerService customerService;

    @Autowired
    public FlowerShopOrderController(ProductService productService, OrderService orderService, CustomerService customerService) {
        this.productService = productService;
        this.orderService = orderService;
        this.customerService = customerService;
    }

    static String getCustomerName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/order")
    public String showOrderPage(Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "order";
    }

    @PostMapping("/order")
    public String placeOrder(OrderDto orderDto, RedirectAttributes redirectAttributes) {
        orderDto = orderService.createOrder(getCustomerName(), orderDto);

        Optional<CustomerDto> currentCustomer = customerService.getCustomerByUsername(getCustomerName());
        if (currentCustomer.isPresent()) {
            CustomerDto customerDto = currentCustomer.get();
            redirectAttributes.addFlashAttribute("customer", customerDto);
        }
        redirectAttributes.addFlashAttribute("order", orderDto);
        redirectAttributes.addFlashAttribute("totalPrice", calculateTotalPrice(orderDto));

        return "redirect:/order/success";
    }

    private Double calculateTotalPrice(OrderDto orderDto) {
        double totalPrice = 0.0;
        for (OrderItemDto orderItemDto : new ArrayList<>(orderDto.getItems())) {
            Optional<ProductDto> productDto = productService.getProductById(orderItemDto.getProductId());
            if (productDto.isPresent()) {
                totalPrice += productDto.get().getPrice() * orderItemDto.getQuantity()
                        * orderItemDto.getQuantity();
            }
        }
        return totalPrice;
    }


    @GetMapping("/order/success")
    public String showSuccessPage() {
        return "order-success";
    }
}
