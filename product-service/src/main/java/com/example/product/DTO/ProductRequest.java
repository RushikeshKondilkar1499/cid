package com.example.product.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequest(@NotBlank @Size(max = 100) String name, 
		@Size(max = 500) String description,
		@NotNull @DecimalMin("0.01") BigDecimal price) {

}
