package com.project.productsmanagement.services;

import com.project.productsmanagement.dao.ProductDaoImpl;
import com.project.productsmanagement.model.Category;
import com.project.productsmanagement.model.Product;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductDaoImpl productDao;

    public void addProduct(Product product) throws DataIntegrityViolationException, ConstraintViolationException {
        productDao.addProduct(product);
    }
    public Optional<Product> getProductById(Long id) {

        return productDao.getProductById(id);
    }
    public Optional<Product> getProductByCode(String code) {
        return productDao.getProductByCode(code);
    }
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public void deleteProductById(Long id) {
        productDao.deleteProductById(id);
    }

    public void deleteProductByCode(String code) {
        productDao.deleteProductByCode(code);
    }

    public void addProductToCategory(Long productId, String categoryCode) {
        productDao.addProductToCategory(productId, categoryCode);

    }

    public void updateProduct(Product product) throws DataIntegrityViolationException, ConstraintViolationException{
       productDao.updateProduct(product);
    }
}
