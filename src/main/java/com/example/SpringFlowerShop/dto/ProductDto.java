package com.example.SpringFlowerShop.dto;

public class ProductDto {
    Long id;
    String name;
    double price;
    int height;

    public ProductDto() {
    }

    public ProductDto(String name, double price, int height) {
        this.name = name;
        this.price = price;
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
