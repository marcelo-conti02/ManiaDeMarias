package com.mconti.ManiaDeMaria.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mconti.ManiaDeMaria.models.Product;
import com.mconti.ManiaDeMaria.models.Product.CreateProduct;
import com.mconti.ManiaDeMaria.models.Product.UpdateProduct;
import com.mconti.ManiaDeMaria.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> obj = this.productService.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product obj = this.productService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Validated(CreateProduct.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Product obj){
        this.productService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdateProduct.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Product obj, @PathVariable Long id){
        obj.setId(id);
        this.productService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
