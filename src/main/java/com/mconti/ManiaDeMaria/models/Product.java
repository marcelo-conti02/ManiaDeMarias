package com.mconti.ManiaDeMaria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Product {
        public interface CreateProduct{}
        public interface UpdateProduct{}

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "product_name", length = 100, nullable = false, unique = true)
        @NotNull(groups = {CreateProduct.class, UpdateProduct.class})
        @NotEmpty(groups = {CreateProduct.class, UpdateProduct.class})
        private String productName;

        @Column(name = "product_type", length = 100, nullable = false)
        @NotNull(groups = {CreateProduct.class, UpdateProduct.class})
        @NotEmpty(groups = {CreateProduct.class, UpdateProduct.class})
        private String productType;

        @Column(name = "product_price", length = 50, nullable = false)
        @NotNull(groups = {CreateProduct.class, UpdateProduct.class})
        private Float productPrice;
}