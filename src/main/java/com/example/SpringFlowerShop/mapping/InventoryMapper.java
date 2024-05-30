package com.example.SpringFlowerShop.mapping;

import com.example.SpringFlowerShop.dto.InventoryDto;
import com.example.SpringFlowerShop.entity.Inventory;

public class InventoryMapper {
    public InventoryDto mapToInventoryDto(Inventory inventory) {
        InventoryDto dto = new InventoryDto();
        dto.setProductId(inventory.getProduct().getId());
        dto.setQuantity(inventory.getQuantity());
        dto.setShipmentDate(inventory.getShipmentDate());
        return dto;
    }

    public Inventory mapToInventoryEntity(InventoryDto dto) {
        Inventory inventory = new Inventory();
        //inventory.setProduct(dto.getProductId());
        inventory.setQuantity(dto.getQuantity());
        inventory.setShipmentDate(dto.getShipmentDate());
        return inventory;
    }
}
