package com.project.productsmanagement.controller;

import com.project.productsmanagement.model.Category;
import com.project.productsmanagement.services.CategoryService;
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
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody Category category){
        try{
            categoryService.addCategory(category);
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot save this category");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Category added!");
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);
        return categoryOptional.map(category -> ResponseEntity.status(HttpStatus.OK).body(category)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Category> getCategoryByCode(@PathVariable String code){
        Optional<Category> categoryOptional = categoryService.getCategoryByCode(code);
        return categoryOptional.map(category -> ResponseEntity.status(HttpStatus.OK).body(category)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete a category");
    }

    @PutMapping
    public ResponseEntity<String> updateCategory(@RequestBody Category category){
        try{
            categoryService.updateCategory(category);
        }catch (DataIntegrityViolationException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Category code should be unique");
        }
        return ResponseEntity.status(HttpStatus.OK).body("category Updated!");

    }
}
