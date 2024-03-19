package com.project.productsmanagement.controller;

import com.project.productsmanagement.dao.ProductDaoImpl;
import com.project.productsmanagement.model.Product;
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
    ProductDaoImpl productDao;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        try {
            productDao.addProduct(product);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot save this product");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Product added!");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productDao.getProductById(id);
        return productOptional.map(product -> ResponseEntity.status(HttpStatus.OK).body(product)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        Optional<Product> productOptional = productDao.getProductByCode(code);
        return productOptional.map(product -> ResponseEntity.status(HttpStatus.OK).body(product)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productDao.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete a product");
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        try {
            productDao.updateProduct(product);
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The code should be unique");
        } catch (ConstraintViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The price should be >0 ");
        }
        return ResponseEntity.status(HttpStatus.OK).body("product Updated!");
    }
}
