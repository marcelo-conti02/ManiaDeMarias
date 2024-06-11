package com.mconti.ManiaDeMaria.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mconti.ManiaDeMaria.models.User;
import com.mconti.ManiaDeMaria.repositories.UserRepository;
import com.mconti.ManiaDeMaria.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);
        
        if(Objects.isNull(user))
            throw new UsernameNotFoundException("Usuário não encontrado" + username);
        
        return new UserSpringSecurity(user.getId(), user.getUserName(), user.getUserPassword(), user.getProfiles());
    }

}
