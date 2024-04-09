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
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "product_name", length = 100, nullable = false, unique = true)
        @NotNull
        @NotEmpty
        private String productName;

        @Column(name = "product_type", length = 100, nullable = false)
        @NotNull
        @NotEmpty
        private String productType;

        public Product() {
        }

        public Product(Long id, String productName, String productType) {
                this.id = id;
                this.productName = productName;
                this.productType = productType;
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

        @Override
        public boolean equals(Object o) {
                if (o == this)
                        return true;
                if (!(o instanceof Product)) {
                        return false;
                }
                Product product = (Product) o;
                return Objects.equals(id, product.id) && Objects.equals(productName, product.productName)
                                && Objects.equals(productType, product.productType);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, productName, productType);
        }
}