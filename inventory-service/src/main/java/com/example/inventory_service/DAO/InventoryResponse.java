package com.example.inventory_service.DAO;

import com.example.inventory_service.Entity.InventoryItem;

public record InventoryResponse(Long productId, int quantity) {

    public static InventoryResponse from(InventoryItem item) {
        return new InventoryResponse(item.getProductId(), item.getQuantity());
    }
}