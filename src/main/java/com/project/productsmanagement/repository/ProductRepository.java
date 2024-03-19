package com.project.productsmanagement.repository;

import com.project.productsmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Optional<Product> findByCode(String code);
    public void deleteByCode(String code);
}
