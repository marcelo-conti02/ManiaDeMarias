package com.mconti.ManiaDeMaria.security;

import java.util.Objects;

import com.mconti.ManiaDeMaria.services.UserService;
import com.mconti.ManiaDeMaria.services.exceptions.AuthorizationException;

public class Authenticated {
     public Long isAuthenticated(){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();
        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");
        return userSpringSecurity.getId();
    }
}
