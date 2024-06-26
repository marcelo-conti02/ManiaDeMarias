package com.mconti.ManiaDeMaria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    @NotNull
    private Long userId;

    @Column(name = "total_price")
    @NotNull
    private Float totalPrice;
}
