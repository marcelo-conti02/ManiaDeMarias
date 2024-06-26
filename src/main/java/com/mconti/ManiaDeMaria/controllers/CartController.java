package com.mconti.ManiaDeMaria.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mconti.ManiaDeMaria.services.CartProductsService;
import com.mconti.ManiaDeMaria.services.CartService;

import jakarta.validation.Valid;

import com.mconti.ManiaDeMaria.DTO.CartProductsDTO;
import com.mconti.ManiaDeMaria.models.Cart;
import com.mconti.ManiaDeMaria.models.CartProducts;
import com.mconti.ManiaDeMaria.models.Product;
import com.mconti.ManiaDeMaria.models.Product.CreateProduct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductsService cartProductsService;

    @GetMapping("/{id}")
    public List<CartProducts> getCard(@PathVariable Iterable<Long> id) {
        List<CartProducts> products = cartProductsService.findAllById(id);
        return products;
    }

    @PostMapping
    @Validated(CreateProduct.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Cart obj){
        this.cartService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping
    public ResponseEntity<CartProducts> createCartProduct(@RequestBody CartProductsDTO cartProductsDTO) {

        CartProducts cartProduct = new CartProducts();
        Cart cart = new Cart();
        Product product = new Product();

        cart.setId(cartProductsDTO.getCartId());
        cart.setUserId(cartProductsDTO.getUserId());
        product.setId(cartProductsDTO.getProductId());
        product.setProductPrice(cartProductsDTO.getProductPrice());

        CartProducts createdCartProduct = cartProductsService.create(cartProduct, cart, product);

        return new ResponseEntity<>(createdCartProduct, HttpStatus.CREATED);
    }
}
