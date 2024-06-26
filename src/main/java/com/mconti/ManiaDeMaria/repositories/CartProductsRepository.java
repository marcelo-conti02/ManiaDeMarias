package com.mconti.ManiaDeMaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mconti.ManiaDeMaria.models.CartProducts;

@Repository
public interface CartProductsRepository extends JpaRepository<CartProducts, Long> {

}
