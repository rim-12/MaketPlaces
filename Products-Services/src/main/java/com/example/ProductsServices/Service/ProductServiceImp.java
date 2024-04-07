package com.example.ProductsServices.Service;

import com.example.ProductsServices.Entity.Category;
import com.example.ProductsServices.Entity.DTO.CategoryResponseDto;
import com.example.ProductsServices.Entity.DTO.ProductRequestDto;
import com.example.ProductsServices.Entity.DTO.ProductResponseDto;
import com.example.ProductsServices.Entity.Product;
import com.example.ProductsServices.Entity.SousCategory;
import com.example.ProductsServices.Repository.CategoryRepository;
import com.example.ProductsServices.Repository.ProductRepository;
import com.example.ProductsServices.Repository.SousCategoryRepository;
import com.example.ProductsServices.Utilis.MappingProfile;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SousCategoryRepository sousCategoryRepository;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(MappingProfile::mapToDtos)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto ProductDto) {
//        Product product = MappingProfile.mapToEntity(ProductDto);
//        Category category=categoryRepository.getById(ProductDto.getCategoryId());
//        SousCategory sousCategory=sousCategoryRepository.getById(ProductDto.getSouscategoryId());
//        product.setCategory(category);
//        product.setSousCategory(sousCategory);
//        return MappingProfile.mapToDto(sousCategoryRepository.save(sousCategory));
        Product product = MappingProfile.mapToEntity(ProductDto);
        Category category = categoryRepository.getById(ProductDto.getCategoryId());
        // Récupérer la sous-catégorie par son identifiant
        SousCategory sousCategory = sousCategoryRepository.getById(ProductDto.getSouscategoryId());

        // Vérifier si la sous-catégorie existe
        if (sousCategory == null) {
            // Gérer l'erreur ici, par exemple :
            throw new IllegalArgumentException("La sous-catégorie spécifiée n'existe pas.");
        }

        product.setCategory(category);
        product.setSousCategory(sousCategory);

        // Enregistrer le produit avec la sous-catégorie associée
        Product savedProduct = productRepository.save(product);

        // Mapper et retourner la réponse DTO du produit sauvegardé
        return MappingProfile.mapToDto(savedProduct);
    }

    @Override
    public ProductResponseDto getProductById(Long id) throws Exception {
            var product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("product not found"));
            return MappingProfile.mapToDto(product);

    }

//    @Override
//    public ProductResponseDto getProductByName(String name) throws Exception {
//        return null;
//    }


    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto ProductDto) throws Exception {
        var product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        product.setProduct_name(ProductDto.getProduct_name());
        product.setPrice(ProductDto.getPrice());
        product.setDescription(ProductDto.getDescription());
        return MappingProfile.mapToDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        var product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public ProductResponseDto getProductByName(String productName) throws Exception {
        Product product = productRepository.findByProductName(productName);
        if (product == null) {
            throw new NotFoundException("Product not found with name: " + productName);
        }
        return MappingProfile.mapToDto(product);
    }


}
