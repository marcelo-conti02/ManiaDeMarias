package com.mconti.ManiaDeMaria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
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

        public Product() {
        }

        public Product(Long id, String productName, String productType, Float productPrice) {
                this.id = id;
                this.productName = productName;
                this.productType = productType;
                this.productPrice = productPrice;
        }

        public Long getId() {
                return this.id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getProductName() {
                return this.productName;
        }

        public void setProductName(String productName) {
                this.productName = productName;
        }

        public String getProductType() {
                return this.productType;
        }

        public void setProductType(String productType) {
                this.productType = productType;
        }

        public Float getProductPrice() {
                return this.productPrice;
        }

        public void setProductPrice(Float productPrice) {
                this.productPrice = productPrice;
        }

        @Override
        public boolean equals(Object o) {
                if (o == this)
                        return true;
                if (!(o instanceof Product)) {
                        return false;
                }
                Product product = (Product) o;
                return Objects.equals(id, product.id) 
                                && Objects.equals(productName, product.productName)
                                && Objects.equals(productType, product.productType) 
                                && Objects.equals(productPrice, product.productPrice);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, productName, productType, productPrice);
        }
}