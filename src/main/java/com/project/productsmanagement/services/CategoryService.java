package com.project.productsmanagement.services;

import com.project.productsmanagement.dao.CategoryDaoImpl;
import com.project.productsmanagement.model.Category;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryDaoImpl categoryDao;

    public void addCategory(Category category) throws DataIntegrityViolationException, ConstraintViolationException {
        categoryDao.addCategory(category);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryDao.getCategoryById(id);
    }

    public Optional<Category> getCategoryByCode(String code) {
        return categoryDao.getCategoryByCode(code);
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public void deleteCategory(Long id) {
        categoryDao.deleteCategory(id);
    }

    public void deleteCategoryByCode(String code) {
        categoryDao.deleteCategoryByCode(code);
    }

    public void updateCategory(Category category) throws DataIntegrityViolationException, ConstraintViolationException{
        categoryDao.updateCategory(category);
    }
}
