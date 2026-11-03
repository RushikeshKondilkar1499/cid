package com.example.product.Exceptions;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(Long id) {
		super("Product not found for: "+id);
	}
}
