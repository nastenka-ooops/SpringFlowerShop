package com.example.SpringFlowerShop.controller;

import com.example.SpringFlowerShop.dto.UserDto;
import com.example.SpringFlowerShop.entity.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RegistrationControllerTest {
    @Autowired
    public MockMvc mockMvc;
    UserDto userDto = new UserDto();

    @BeforeEach
    public void initUser(){

        userDto.setFirstName("first name");
        userDto.setLastName("last name");
        userDto.setAddress("address");
        userDto.setPhone("phone");
        userDto.setEmail("testUser");
        userDto.setPassword("password");
        userDto.setConfirmPassword("password");
        userDto.setRole(Role.CUSTOMER);

    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testRegisterUser_ValidUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .flashAttr("userForm", userDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void testRegisterUser_PasswordMismatch() throws Exception {
        userDto.setConfirmPassword("wrongPassword");

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .flashAttr("userForm", userDto))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("passwordError"));
    }

    @Test
    public void testRegisterUser_UsernameExists() throws Exception {
        userDto.setEmail("nastenka.brutskaya@mail.ru");
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                    .flashAttr("userForm", userDto))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("usernameError"));
    }
}
