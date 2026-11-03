package com.example.product.DTO;

import java.math.BigDecimal;

import com.example.product.Entity.Product;

public record ProductResponse(Long id, String name, String description, BigDecimal price) {
	
	public static ProductResponse from(Product p) {
		return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice());
	}

}
