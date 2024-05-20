package com.example.SpringFlowerShop.service;

import com.example.SpringFlowerShop.dto.InventoryDto;
import com.example.SpringFlowerShop.dto.InventoryWithProductInfoDto;
import com.example.SpringFlowerShop.entity.Inventory;
import com.example.SpringFlowerShop.entity.Product;
import com.example.SpringFlowerShop.mapping.InventoryMapper;
import com.example.SpringFlowerShop.mapping.InventoryWithProductInfoMapper;
import com.example.SpringFlowerShop.repository.InventoryRepository;
import com.example.SpringFlowerShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper = new InventoryMapper();
    private final InventoryWithProductInfoMapper inventoryWithProductInfoMapper = new InventoryWithProductInfoMapper();
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Optional<InventoryDto> getInventoryByProductId(Long productId){
        return inventoryRepository.findById(productId).map(inventoryMapper::mapToInventoryDto);
    }

    public InventoryWithProductInfoDto getInventoryByProductIdWithProductInfo(Long productId){
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory with product id " + productId + " not found"));
        Product product = inventory.getProduct();
        return inventoryWithProductInfoMapper
                .mapToInventoryWithProductInfoDto(inventory, product);
    }

    public List<InventoryDto> getAllInventories(){
        return inventoryRepository.findAll().stream().map(inventoryMapper::mapToInventoryDto)
                .collect(Collectors.toList());
    }

    public InventoryDto createInventory (InventoryDto inventoryDto){
        Long productId = inventoryDto.getProductId();

        if (!productRepository.existsById(productId)){
            throw new IllegalArgumentException("Product with id " + productId + " does not exist");
        }

        Inventory newInventory = inventoryMapper.mapToInventoryEntity(inventoryDto);
        newInventory = inventoryRepository.save(newInventory);
        return inventoryMapper.mapToInventoryDto(newInventory);
    }

    public Optional<InventoryDto> updateInventoryByProductId(Long productId, InventoryDto inventoryDto){
        return inventoryRepository.findById(productId).map(inventory -> {
            inventory.setQuantity(inventoryDto.getQuantity());
            inventory.setShipmentDate(inventoryDto.getShipmentDate());
            inventory = inventoryRepository.save(inventory);
            return inventoryMapper.mapToInventoryDto(inventory);
        });
    }

    public boolean deleteInventoryByProductId(Long productId){
        if (inventoryRepository.existsById(productId)){
            inventoryRepository.deleteById(productId);
            return true;
        } else {
            return false;
        }
    }
}
