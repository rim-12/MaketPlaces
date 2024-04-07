package com.example.ProductsServices.Controller;

import com.example.ProductsServices.Entity.DTO.CategoryRequestDto;
import com.example.ProductsServices.Service.CategoryService;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCategories() {
        try {
            return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch categories", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
//        try {
//            return new ResponseEntity<>(categoryService.createCategory(categoryRequestDto), HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to create category", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        try{
            return new ResponseEntity<>(categoryService.createCategory(categoryRequestDto),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to create category", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        try {
            return new ResponseEntity<>(categoryService.updateCategory(id, categoryRequestDto), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Category with ID " + id + " deleted successfully");
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete Category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
