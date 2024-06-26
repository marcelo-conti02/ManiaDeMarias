package com.mconti.ManiaDeMaria.services;

import com.mconti.ManiaDeMaria.models.Cart;
import com.mconti.ManiaDeMaria.models.CartProducts;
import com.mconti.ManiaDeMaria.models.Product;
import com.mconti.ManiaDeMaria.repositories.CartProductsRepository;
import com.mconti.ManiaDeMaria.security.Authenticated;
import com.mconti.ManiaDeMaria.services.CartService;
import com.mconti.ManiaDeMaria.services.exceptions.AuthorizationException;
import com.mconti.ManiaDeMaria.services.exceptions.ObjectNotFoundException;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartProductsService {
    @Autowired
    private CartProductsRepository cartProductsRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private Authenticated authenticated;

    public CartProducts findById(Long id) {
        authenticated.isAuthenticated();
        Optional<CartProducts> cartProduct = this.cartProductsRepository.findById(id);
        return cartProduct.orElseThrow(() -> new ObjectNotFoundException(
                "CartProduct not found! Id:" + id));
    }

    public List<CartProducts> findAllById(Iterable<Long> id) {
        authenticated.isAuthenticated();
        Optional<List<CartProducts>> cartProducts = Optional.of(this.cartProductsRepository.findAllById(id));
        return cartProducts.orElseThrow(() -> new ObjectNotFoundException(
                "CartProduct not found! Id:" + id));
    }

    @Transactional
    public CartProducts create(CartProducts cartProduct, Cart cart, Product product) {
        Long userId = authenticated.isAuthenticated();
        if (userId != cart.getUserId())
            throw new AuthorizationException("Acesso negado!");

        cartProduct.setCartId(cart.getId());
        cartProduct.setProductId(product.getId());
        cartService.update(cart, product.getProductPrice());
        cartProduct = this.cartProductsRepository.save(cartProduct);

        return cartProduct;
    }

    public void delete(CartProducts cartProduct) {

    }
}
