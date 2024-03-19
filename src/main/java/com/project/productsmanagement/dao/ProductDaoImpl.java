package com.project.productsmanagement.dao;

import com.project.productsmanagement.model.Product;
import com.project.productsmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoImpl implements ProductDao{

    @Autowired
    ProductRepository productRepository;
    @Override
    public void addProduct(Product product) throws DataIntegrityViolationException {
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

        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setCategoryCode(categoryCode);
            productRepository.save(product);
        }

    }
}
