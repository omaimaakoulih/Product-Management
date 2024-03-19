package com.project.productsmanagement.controller;

import com.project.productsmanagement.dao.CategoryDaoImpl;
import com.project.productsmanagement.model.Category;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryDaoImpl categoryDao;

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody Category category){
        try{
            categoryDao.addCategory(category);
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot save this category");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Category added!");
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryDao.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryDao.getCategoryById(id);
        return categoryOptional.map(category -> ResponseEntity.status(HttpStatus.OK).body(category)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Category> getCategoryByCode(@PathVariable String code){
        Optional<Category> categoryOptional = categoryDao.getCategoryByCode(code);
        return categoryOptional.map(category -> ResponseEntity.status(HttpStatus.OK).body(category)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryDao.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete a category");
    }

    @PutMapping
    public ResponseEntity<String> updateCategory(@RequestBody Category category){
        try{
            categoryDao.updateCategory(category);
        }catch (DataIntegrityViolationException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Category code should be unique");
        }
        return ResponseEntity.status(HttpStatus.OK).body("category Updated!");

    }
}
