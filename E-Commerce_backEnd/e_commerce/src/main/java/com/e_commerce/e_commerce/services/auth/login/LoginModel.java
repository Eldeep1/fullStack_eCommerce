package com.e_commerce.e_commerce.services.auth.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_commerce.e_commerce.models.User;
@Component
public class LoginModel {
    @Autowired
    private  LoginRepository loginRepository;



    public User login(String email, String password) {
        return loginRepository.findByEmailAndPassword(email, password);
    }
}
