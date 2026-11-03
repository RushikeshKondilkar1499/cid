package com.example.inventory_service.Controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.example.inventory_service.DAO.InventoryResponse;
import com.example.inventory_service.DAO.ReserveRequest;
import com.example.inventory_service.DTO.StockRequest;
import com.example.inventory_service.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PutMapping("/{productId}")
    public InventoryResponse setStock(@PathVariable Long productId,
                                      @Valid @RequestBody StockRequest request) {
        return service.setStock(productId, request.quantity());
    }

    @GetMapping("/{productId}")
    public InventoryResponse getStock(@PathVariable Long productId) {
        return service.getStock(productId);
    }

    @PostMapping("/{productId}/reserve")
    public InventoryResponse reserve(@PathVariable Long productId,
                                     @Valid @RequestBody ReserveRequest request) {
        return service.reserve(productId, request.quantity());
    }
} 