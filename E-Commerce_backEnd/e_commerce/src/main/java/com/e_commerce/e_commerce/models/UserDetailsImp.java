package com.e_commerce.e_commerce.models;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.repositories.UserRepository;


@Service
public class UserDetailsImp implements UserDetailsService{

    private final UserRepository repository;
    

    public UserDetailsImp(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        User user =repository.findByEmail(email);
        
        System.out.println(repository.findByEmail(email).getFirstName());
        return repository.findByEmail(email);
    }

}
