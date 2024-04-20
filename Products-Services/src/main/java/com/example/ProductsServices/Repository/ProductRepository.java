package com.example.ProductsServices.Repository;

import com.example.ProductsServices.Entity.DTO.ProductResponseDto;
import com.example.ProductsServices.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    //ProductResponseDto getProductByName(String name) throws Exception;
}
