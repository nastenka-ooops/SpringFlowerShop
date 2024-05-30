package com.example.SpringFlowerShop.mapping;

import com.example.SpringFlowerShop.dto.ProductDto;
import com.example.SpringFlowerShop.entity.Product;

public class ProductMapper {
    public ProductDto mapToProductDto(Product entity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(entity.getId());
        productDto.setName(entity.getName());
        productDto.setPrice(entity.getPrice());
        productDto.setHeight(entity.getHeight());
        return productDto;
    }

    public Product mapToProductEntity(ProductDto productDto) {
        Product entity = new Product();
        entity.setId(productDto.getId());
        entity.setName(productDto.getName());
        entity.setPrice(productDto.getPrice());
        entity.setHeight(productDto.getHeight());
        return entity;
    }
}
