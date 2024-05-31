package com.mconti.ManiaDeMaria.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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

    @Column(name = "user_name", length = 100, nullable = false, unique = true)
    @NotNull(groups = { CreateUser.class })
    @NotEmpty(groups = { CreateUser.class })
    private String userName;

    @Column(name = "user_password", length = 100, nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull(groups = { CreateUser.class, UpdateUser.class })
    @NotEmpty(groups = { CreateUser.class, UpdateUser.class })
    private String userPassword;
}
