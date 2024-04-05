package com.mconti.ManiaDeMaria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = Product.TABLE_NAME)
public class Product {
        public static final String TABLE_NAME = "product";

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true)
        private Long id;

        @Column(name = "product_name", length = 100, nullable = false, unique = true)
        private String productName;

        @Column(name = "product_type", length = 100, nullable = false)
        private String productType;


}