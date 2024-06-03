package com.example.SpringFlowerShop.controller;

import com.example.SpringFlowerShop.dto.UserDto;
import com.example.SpringFlowerShop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userForm") @Valid UserDto userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "register";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "register";
        }

        return "redirect:/login";
    }
}
