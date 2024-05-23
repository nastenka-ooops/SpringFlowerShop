package com.example.SpringFlowerShop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoController {
    @GetMapping("/welcome")
    public String welcome() {
        return "This is unprotected page";
    }

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String pageForUser() {
        return "This is page for only users";
    }


    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String pageForAdmins() {
        return "This is page for only admins";
    }


    @GetMapping("/all")
    public String pageForAll() {
        return "This is page for all employees";
    }

}
