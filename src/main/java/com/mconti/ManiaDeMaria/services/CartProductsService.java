package com.mconti.ManiaDeMaria.services;

import com.mconti.ManiaDeMaria.models.Cart;
import com.mconti.ManiaDeMaria.models.CartProducts;
import com.mconti.ManiaDeMaria.models.Product;
import com.mconti.ManiaDeMaria.security.Authenticated;
import com.mconti.ManiaDeMaria.services.CartService;
import com.mconti.ManiaDeMaria.services.exceptions.AuthorizationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartProductsService {
    @Autowired
    private CartService cartService;

    @Autowired
    private Authenticated authenticated;

    @Transactional
    public CartProducts create(CartProducts cartProduct, Cart cart, Product product) {
        Long userId = authenticated.isAuthenticated();
        if(userId != cart.getUserId())
            throw new AuthorizationException("Acesso negado!");
        
        cartProduct.setCartId(cart.getId());
        cartProduct.setProductId(product.getId());
        return cartProduct;
    }
}
