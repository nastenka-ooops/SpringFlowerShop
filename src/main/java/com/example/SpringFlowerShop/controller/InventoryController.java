package com.example.SpringFlowerShop.controller;

import com.example.SpringFlowerShop.dto.InventoryDto;
import com.example.SpringFlowerShop.dto.InventoryWithProductInfoDto;
import com.example.SpringFlowerShop.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {
    private final InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inventories")
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto){
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
    }

    @GetMapping("/inventories")
    public ResponseEntity<List<InventoryDto>> getAllInventories(){
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @GetMapping("/inventories/{productId}")
    public ResponseEntity<InventoryDto> getInventoryByProductId(@PathVariable Long productId){
        return inventoryService.getInventoryByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/inventories/{productId}/productInfo")
    public ResponseEntity<InventoryWithProductInfoDto> getInventoryWithProductInfoByProductId
            (@PathVariable Long productId){
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdWithProductInfo(productId));
    }

    @PutMapping("/inventories/{productId}")
    public ResponseEntity<InventoryDto> updateInventoryByProductId(@PathVariable Long productId,
                                                                   @RequestBody InventoryDto inventoryDto){
        return inventoryService.updateInventoryByProductId(productId, inventoryDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/inventories/{id}")
    public ResponseEntity<Void> deleteInventoryByProductId(@PathVariable Long productId){
        if (inventoryService.deleteInventoryByProductId(productId))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
