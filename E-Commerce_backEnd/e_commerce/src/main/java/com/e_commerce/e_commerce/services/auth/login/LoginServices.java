package com.e_commerce.e_commerce.services.auth.login;

import com.e_commerce.e_commerce.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {
    @Autowired
    private  LoginModel loginModel;

  
    public User login(String email, String password){
        return loginModel.login(email, password);
    }
}
