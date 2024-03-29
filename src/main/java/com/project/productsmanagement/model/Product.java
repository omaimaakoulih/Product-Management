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

    public static final int MIN = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String code;

    private String libelli;

    @Min(value = MIN, message = "Price should be >0")
    private float price;
    // Many to One relation
    private String categoryCode;

    public Product( String code, String libelli, float price) {
        this.code = code;
        this.libelli = libelli;
        this.price = price;
    }
}
