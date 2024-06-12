package com.mconti.ManiaDeMaria.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mconti.ManiaDeMaria.models.User;
import com.mconti.ManiaDeMaria.models.dto.UserCreateDTO;
import com.mconti.ManiaDeMaria.models.dto.UserUpdateDTO;
import com.mconti.ManiaDeMaria.models.enums.ProfileEnum;
import com.mconti.ManiaDeMaria.repositories.UserRepository;
import com.mconti.ManiaDeMaria.security.UserSpringSecurity;
import com.mconti.ManiaDeMaria.services.exceptions.AuthorizationException;
import com.mconti.ManiaDeMaria.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long id) {
        UserSpringSecurity userSpringSecurity = authenticated();

        if (!Objects.nonNull(userSpringSecurity)
                || !userSpringSecurity.hasRole(ProfileEnum.ADMIN) && !id.equals(userSpringSecurity.getId()))
            throw new AuthorizationException("Acesso negado!");
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário não encontrado! Id:" + id));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj.setUserPassword(this.bCryptPasswordEncoder.encode(obj.getUserPassword()));
        obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setUserPassword(obj.getUserPassword());
        newObj.setUserPassword(this.bCryptPasswordEncoder.encode(obj.getUserPassword()));
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        this.userRepository.deleteById(id);
    }

    public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public User fromDTO(@Valid UserCreateDTO obj) {
        User user = new User();
        user.setUserName(obj.getUsername());
        user.setUserPassword(obj.getPassword());
        return user;
    }

    public User fromDTO(@Valid UserUpdateDTO obj) {
        User user = new User();
        user.setId(obj.getId());
        user.setUserPassword(obj.getPassword());
        return user;
    }
}
