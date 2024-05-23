package com.example.SpringFlowerShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringFlowerShopApplication {

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5);
		System.out.println(bCryptPasswordEncoder.encode("123"));
		SpringApplication.run(SpringFlowerShopApplication.class, args);
	}

}
