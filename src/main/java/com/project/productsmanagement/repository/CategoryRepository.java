package com.project.productsmanagement.repository;

import com.project.productsmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Optional<Category> findByCode(String code);
    public void deleteByCode(String code);
}
