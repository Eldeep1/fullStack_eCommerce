package com.e_commerce.e_commerce.services.auth.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.repositories.UserRepository;
@Component
public class LoginModel {
    @Autowired
    private  UserRepository userRepository;



    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
