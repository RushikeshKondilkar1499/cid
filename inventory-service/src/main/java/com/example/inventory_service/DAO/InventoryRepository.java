package com.example.inventory_service.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.inventory_service.Entity.InventoryItem;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {

	// Atomic: the stock check and the decrement happen in ONE SQL statement.
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("update InventoryItem i set i.quantity = i.quantity - :qty "
			+ "where i.productId = :productId and i.quantity >= :qty")
	int reserve(@Param("productId") Long productId, @Param("qty") int qty);
}