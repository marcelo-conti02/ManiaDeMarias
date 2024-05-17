package com.mconti.ManiaDeMaria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.mconti.ManiaDeMaria.models.Product;
import com.mconti.ManiaDeMaria.repositories.ProductRepository;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    public Product findById(Long id){
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException(
            "Produto não encontrado! Id:" + id
        ));
    }

    @Transactional
    public Product create(Product obj){
        obj.setId(null);
        obj = this.productRepository.save(obj);
        return obj;
    }

    @Transactional
    public Product update(Product obj){
        Product newObj = findById(obj.getId());
        newObj.setProductName(obj.getProductName());
        newObj.setProductType(obj.getProductType());
        newObj.setProductPrice(obj.getProductPrice());
        return this.productRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        this.productRepository.deleteById(id);
    }
}
