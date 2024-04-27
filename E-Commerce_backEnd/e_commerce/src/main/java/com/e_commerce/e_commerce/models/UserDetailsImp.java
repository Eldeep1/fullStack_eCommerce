package com.e_commerce.e_commerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.repositories.UserRepository;

@AllArgsConstructor
@Data
@Service
public class UserDetailsImp implements UserDetailsService{

    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

}
