package com.example.SpringFlowerShop;

import com.example.SpringFlowerShop.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SpringFlowerShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlowerShopApplication.class, args);
    }
}
