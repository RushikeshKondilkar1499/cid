package com.example.inventory_service.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_items")
public class InventoryItem {

	@Id
	private Long productId; // same ID as in Product Service; we store the ID only, never a copy of the
							// product

	@Column(nullable = false)
	private int quantity;

	protected InventoryItem() {
	}

	public InventoryItem(Long productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}