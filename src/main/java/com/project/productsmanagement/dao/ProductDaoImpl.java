package com.project.productsmanagement.dao;

import com.project.productsmanagement.model.Category;
import com.project.productsmanagement.model.Product;
import com.project.productsmanagement.repository.ProductRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoImpl implements ProductDao{

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryDaoImpl categoryDao;

    private static final int MIN = 0;
    @Override
    public void addProduct(Product product) throws DataIntegrityViolationException, ConstraintViolationException {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {

        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> getProductByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteProductByCode(String code) {
        productRepository.deleteByCode(code);
    }

    @Override
    public void addProductToCategory(Long productId, String categoryCode) {

        Optional<Product> productOptional = getProductById(productId);
        Optional<Category> categoryOptional = categoryDao.getCategoryByCode(categoryCode);

        if(productOptional.isPresent() && categoryOptional.isPresent()){
            Product product = productOptional.get();
            product.setCategoryCode(categoryCode);
            updateProduct(product);
        }

    }

    @Override
    public void updateProduct(Product product) throws DataIntegrityViolationException, ConstraintViolationException{
        if(priceIsCorrect(product.getPrice())){
            throw new ConstraintViolationException("Product price must be greater than " + MIN, null);
        }
        productRepository.save(product);
    }
    public boolean priceIsCorrect(float price){
        return price>MIN;
    }
}
