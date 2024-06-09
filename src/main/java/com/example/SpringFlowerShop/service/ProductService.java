package com.example.SpringFlowerShop.service;

import com.example.SpringFlowerShop.dto.ProductDto;
import com.example.SpringFlowerShop.entity.Product;
import com.example.SpringFlowerShop.mapping.ProductMapper;
import com.example.SpringFlowerShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = new ProductMapper();

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(ProductDto productDto) {
        return productMapper.mapToProductDto(
                productRepository.save(productMapper.mapToProductEntity(productDto)));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findByDeletedFalse().stream().map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::mapToProductDto);
    }

    public ProductDto updateProductById(Long id, ProductDto updatedProduct) {
        ProductDto productDto = productMapper.mapToProductDto(
                productRepository.findById(id).orElse(new Product()));
        productDto.setName(updatedProduct.getName());
        productDto.setPrice(updatedProduct.getPrice());
        productDto.setHeight(updatedProduct.getHeight());
        return productMapper.mapToProductDto(
                productRepository.save(productMapper.mapToProductEntity(productDto)));
    }

    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDeleted(true);
        productRepository.save(product);
    }
}
