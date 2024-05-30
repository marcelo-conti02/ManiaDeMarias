package com.mconti.ManiaDeMaria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mconti.ManiaDeMaria.models.User;
import com.mconti.ManiaDeMaria.repositories.UserRepository;
import com.mconti.ManiaDeMaria.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return this.userRepository.findAll();
    }
    
    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(
            "Usuário não encontrado! Id:" + id
        ));
    }

    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setUserPassword(obj.getUserPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        this.userRepository.deleteById(id);
    }
}
