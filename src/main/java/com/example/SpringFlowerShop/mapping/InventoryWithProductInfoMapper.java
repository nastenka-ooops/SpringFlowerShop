package com.example.SpringFlowerShop.mapping;

import com.example.SpringFlowerShop.dto.InventoryWithProductInfoDto;
import com.example.SpringFlowerShop.entity.Inventory;
import com.example.SpringFlowerShop.entity.Product;

public class InventoryWithProductInfoMapper {
    public InventoryWithProductInfoDto mapToInventoryWithProductInfoDto (Inventory inventory, Product product) {
        InventoryWithProductInfoDto dto = new InventoryWithProductInfoDto();
        dto.setProductId(inventory.getProductId());
        dto.setQuantity(inventory.getQuantity());
        dto.setShipmentDate(inventory.getShipmentDate());
        dto.setProductName(product.getName());
        dto.setProductPrice(product.getPrice());
        dto.setProductHeight(product.getHeight());
        return dto;
    }
}
