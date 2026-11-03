package com.example.inventory_service.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventory_service.DAO.InventoryRepository;
import com.example.inventory_service.DAO.InventoryResponse;
import com.example.inventory_service.Entity.InventoryItem;
import com.example.inventory_service.exception.InsufficientStockException;
import com.example.inventory_service.exception.InventoryItemNotFoundException;

@Service
public class InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public InventoryResponse setStock(Long productId, int quantity) {
        InventoryItem item = repository.findById(productId)
                .orElseGet(() -> new InventoryItem(productId, quantity));
        item.setQuantity(quantity);
        log.info("Stock set for product={} quantity={}", productId, quantity);
        return InventoryResponse.from(repository.save(item));
    }

    @Transactional(readOnly = true)
    public InventoryResponse getStock(Long productId) {
        return repository.findById(productId)
                .map(InventoryResponse::from)
                .orElseThrow(() -> new InventoryItemNotFoundException(productId));
    }

    @Transactional
    public InventoryResponse reserve(Long productId, int quantity) {
        if (!repository.existsById(productId)) {
            throw new InventoryItemNotFoundException(productId);
        }
        int updatedRows = repository.reserve(productId, quantity);
        if (updatedRows == 0) {
            log.warn("Reserve rejected: product={} requested={}", productId, quantity);
            throw new InsufficientStockException(productId, quantity);
        }
        log.info("Reserved product={} quantity={}", productId, quantity);
        return getStock(productId);
    }
}