package com.example.product.Entity;

import java.math.BigDecimal;

import org.springframework.context.annotation.Description;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,length = 100)
	private String name;
	
	@Column(length = 500)
	private String description;
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	public Product( String name, String description, BigDecimal price) {
		super();
		
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	
	
	
}
