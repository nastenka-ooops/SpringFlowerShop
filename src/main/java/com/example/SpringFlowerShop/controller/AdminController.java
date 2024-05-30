package com.example.SpringFlowerShop.controller;

import com.example.SpringFlowerShop.dto.ProductDto;
import com.example.SpringFlowerShop.service.ProductService;
import com.example.SpringFlowerShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public AdminController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "add-product";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        ProductDto product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/users")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute ProductDto product) {
        productService.createProduct(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute ProductDto product) {
        product.setId(id);
        productService.updateProductById(id, product);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }
}
