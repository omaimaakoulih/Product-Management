package com.project.productsmanagement.controller;

import com.project.productsmanagement.model.Product;
import com.project.productsmanagement.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        try {
            productService.addProduct(product);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot save this product");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Product added!");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.getProductById(id);
        return productOptional.map(product -> ResponseEntity.status(HttpStatus.OK).body(product)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        Optional<Product> productOptional = productService.getProductByCode(code);
        return productOptional.map(product -> ResponseEntity.status(HttpStatus.OK).body(product)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete a product");
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        try {
            productService.updateProduct(product);
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The code should be unique");
        } catch (ConstraintViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The price should be >0 ");
        }
        return ResponseEntity.status(HttpStatus.OK).body("product Updated!");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> addCategoryToProduct(@PathVariable Long id, @RequestBody String categoryCode){
        try{
            productService.addProductToCategory(id,categoryCode);
        }catch(EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot add product to category");
        }
        return ResponseEntity.status(HttpStatus.OK).body("product added to category!");
    }
}
