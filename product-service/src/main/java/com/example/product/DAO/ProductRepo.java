package com.example.product.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
