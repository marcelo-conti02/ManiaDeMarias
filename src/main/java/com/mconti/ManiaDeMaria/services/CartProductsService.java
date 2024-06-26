package com.mconti.ManiaDeMaria.services;

import com.mconti.ManiaDeMaria.models.Cart;
import com.mconti.ManiaDeMaria.models.CartProducts;
import com.mconti.ManiaDeMaria.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartProductsService {
    @Autowired
    private CartService cartService;

    @Transactional
    public CartProducts create(CartProducts cartProduct) {
       
    }
}
