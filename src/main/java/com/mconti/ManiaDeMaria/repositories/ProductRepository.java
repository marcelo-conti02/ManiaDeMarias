package com.mconti.ManiaDeMaria.repositories;

import com.mconti.ManiaDeMaria.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
