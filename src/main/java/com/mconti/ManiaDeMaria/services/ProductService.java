package com.mconti.ManiaDeMaria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.util.Objects;

import com.mconti.ManiaDeMaria.models.Product;
import com.mconti.ManiaDeMaria.models.enums.ProfileEnum;
import com.mconti.ManiaDeMaria.repositories.ProductRepository;
import com.mconti.ManiaDeMaria.security.UserSpringSecurity;
import com.mconti.ManiaDeMaria.services.exceptions.AuthorizationException;
import com.mconti.ManiaDeMaria.services.exceptions.ObjectNotFoundException;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return this.productRepository.findAll();
    }
    
    public Product findById(Long id){
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElseThrow(() -> new ObjectNotFoundException(
            "Produto não encontrado! Id:" + id
        ));
    }

    @Transactional
    public Product create(Product obj){
        isAdm();
        obj.setId(null);
        obj = this.productRepository.save(obj);
        return obj;
    }

    @Transactional
    public Product update(Product obj){
        isAdm();
        Product newObj = findById(obj.getId());
        newObj.setProductName(obj.getProductName());
        newObj.setProductType(obj.getProductType());
        newObj.setProductPrice(obj.getProductPrice());
        return this.productRepository.save(newObj);
    }

    public void delete(Long id){
        isAdm();
        findById(id);
        this.productRepository.deleteById(id);
    }

    private void isAdm(){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();
        if (Objects.isNull(userSpringSecurity) || !userSpringSecurity.hasRole(ProfileEnum.ADMIN))
            throw new AuthorizationException("Acesso negado!");
        return;
    }
}
