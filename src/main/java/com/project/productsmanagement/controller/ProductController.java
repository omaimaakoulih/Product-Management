package com.project.productsmanagement.controller;

import com.project.productsmanagement.dao.ProductDaoImpl;
import com.project.productsmanagement.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDaoImpl productDao;
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product){
       try{
           productDao.addProduct(product);
       }catch (DataIntegrityViolationException exception){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot save this product");
       }
        return ResponseEntity.status(HttpStatus.OK).body("Product added!");
    }
}
