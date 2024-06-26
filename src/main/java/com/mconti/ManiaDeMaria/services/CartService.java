package com.mconti.ManiaDeMaria.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mconti.ManiaDeMaria.models.Cart;
import com.mconti.ManiaDeMaria.repositories.CartRepository;
import com.mconti.ManiaDeMaria.services.exceptions.AuthorizationException;
import com.mconti.ManiaDeMaria.services.exceptions.ObjectNotFoundException;
import com.mconti.ManiaDeMaria.security.UserSpringSecurity;
import com.mconti.ManiaDeMaria.security.Authenticated;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    Authenticated authenticated;

    @Transactional
    public Cart create(Cart cart) {
        Long userId = authenticated.isAuthenticated();
        cart.setUserId(userId);
        cart.setTotalPrice((float) 0);
        cart = this.cartRepository.save(cart);
        return cart;
    }

    public Cart findById(Long id) {
        authenticated.isAuthenticated();
        Optional<Cart> cart = this.cartRepository.findById(id);
        return cart.orElseThrow(() -> new ObjectNotFoundException(
                "Cart not found! Id:" + id));
    }

    public void delete(Long id) {
        findById(id);
        this.cartRepository.deleteById(id);
    }

    @Transactional
    public Cart update(Cart obj, Float price) {
        Cart newObj = findById(obj.getId());
        newObj.setTotalPrice(obj.getTotalPrice() + price);
        return this.cartRepository.save(newObj); 
    }
}
