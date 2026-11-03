package com.example.product.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.product.DAO.ProductRepo;
import com.example.product.DTO.ProductRequest;
import com.example.product.DTO.ProductResponse;
import com.example.product.Entity.Product;
import com.example.product.Exceptions.ProductNotFoundException;

import jakarta.validation.Valid;

@Service
public class ProductService {
	
	private final static  Logger logger=LoggerFactory.getLogger(ProductService.class);
	
	private ProductRepo productRepo;

	public ProductService(ProductRepo productRepo) {
		super();
		this.productRepo = productRepo;
	}
	
	@Transactional
	public ProductResponse create(ProductRequest productRequest){
		
		Product p=productRepo.save(new Product(productRequest.name(),productRequest.description(),productRequest.price()));
		logger.info("Created product with id: "+p.getId());
		return ProductResponse.from(p);
	}
	
	@Transactional(readOnly=true)
	public ProductResponse getById(Long id) {
		
		Product p=productRepo.findById(id).orElseThrow(()->new ProductNotFoundException(id));
		
		return ProductResponse.from(p);
	}
	
	@Transactional(readOnly = true)
    public List<ProductResponse> getAll() {
        return productRepo.findAll().stream().map(ProductResponse::from).toList();
    }

}
