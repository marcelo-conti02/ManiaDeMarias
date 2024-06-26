package com.mconti.ManiaDeMaria.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mconti.ManiaDeMaria.models.Cart;
import com.mconti.ManiaDeMaria.models.enums.ProfileEnum;
import com.mconti.ManiaDeMaria.repositories.CartRepository;
import com.mconti.ManiaDeMaria.services.exceptions.AuthorizationException;
import com.mconti.ManiaDeMaria.services.exceptions.ObjectNotFoundException;
import com.mconti.ManiaDeMaria.security.UserSpringSecurity;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Transactional
    public Cart create(Cart cart) {
        Long userId = isAuthenticated();
        cart.setUserId(userId);
        cart.setTotalPrice((float) 0);
        cart = this.cartRepository.save(cart);
        return cart;
    }

    public Cart findById(Long id) {
        isAuthenticated();
        Optional<Cart> cart = this.cartRepository.findById(id);
        return cart.orElseThrow(() -> new ObjectNotFoundException(
                "Cart not found! Id:" + id));
    }

    public void delete(Long id, Long userId) {
        findById(id);
        this.cartRepository.deleteById(id);
    }

    private Long isAuthenticated(){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();
        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");
        return userSpringSecurity.getId();
    }
}
