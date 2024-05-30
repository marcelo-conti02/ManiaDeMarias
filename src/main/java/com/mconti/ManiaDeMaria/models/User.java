package com.mconti.ManiaDeMaria.models;

import com.mconti.ManiaDeMaria.models.Product.CreateProduct;
import com.mconti.ManiaDeMaria.models.Product.UpdateProduct;

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
public class User {
    public interface CreateUser {}
    public interface UpdateUser {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 100, nullable = false)
    @NotNull(groups = { CreateProduct.class })
    @NotEmpty(groups = { CreateProduct.class })
    private String userName;

    @Column(name = "user_password", length = 100, nullable = false)
    @NotNull(groups = { CreateProduct.class, UpdateProduct.class })
    @NotEmpty(groups = { CreateProduct.class, UpdateProduct.class })
    private String userPassword;
}
