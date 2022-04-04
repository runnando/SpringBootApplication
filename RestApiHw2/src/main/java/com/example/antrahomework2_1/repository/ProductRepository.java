package com.example.antrahomework2_1.repository;

import com.example.antrahomework2_1.domain.Product;
import com.example.antrahomework2_1.domain.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends JpaRepository<ProductDTO,Integer> {
    ProductDTO findByName(String name);
}
