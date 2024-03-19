package com.project.productsmanagement.dao;

import com.project.productsmanagement.model.Product;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    public void addProduct(Product product) throws DataIntegrityViolationException, ConstraintViolationException;
    public Optional<Product> getProductById(Long id);
    public Optional<Product> getProductByCode(String code);
    public List<Product> getAllProducts();
    public void deleteProductById(Long id);
    public void deleteProductByCode(String code);
    public void addProductToCategory(Long productId,String categoryCode);
    public void updateProduct(Product product);
}
