package com.project.productsmanagement.controller;

import com.project.productsmanagement.dao.CategoryDaoImpl;
import com.project.productsmanagement.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
