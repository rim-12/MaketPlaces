package com.example.ProductsServices.Service;

import com.example.ProductsServices.Entity.DTO.ProductRequestDto;
import com.example.ProductsServices.Entity.DTO.ProductResponseDto;
import com.example.ProductsServices.Entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto createProduct(ProductRequestDto ProductDto);
    ProductResponseDto getProductById(Long id) throws Exception;
    //ProductResponseDto getProductByName(String name) throws Exception;
    ProductResponseDto updateProduct(Long id, ProductRequestDto ProductDto) throws Exception;
    void deleteProduct(Long id) throws Exception;
}
