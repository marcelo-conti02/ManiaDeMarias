package com.mconti.ManiaDeMaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mconti.ManiaDeMaria.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
