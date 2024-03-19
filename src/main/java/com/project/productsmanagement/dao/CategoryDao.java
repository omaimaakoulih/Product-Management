package com.project.productsmanagement.dao;

import com.project.productsmanagement.model.Category;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {

    public void addCategory(Category category) throws DataIntegrityViolationException;
    public Optional<Category> getCategoryById(Long id);
    public Optional<Category> getCategoryByCode(String code);
    public List<Category> getAllCategories();
    public void deleteCategory(Long id);
    public void deleteCategoryByCode(String code);
}
