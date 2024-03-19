package com.project.productsmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String code;

    private String libelli;

    @Min(value = 0, message = "Price should be >0")
    private float price;

    private String categoryCode;

    public Product(Long id, String code, String libelli, float price) {
        this.id = id;
        this.code = code;
        this.libelli = libelli;
        this.price = price;
    }
}
