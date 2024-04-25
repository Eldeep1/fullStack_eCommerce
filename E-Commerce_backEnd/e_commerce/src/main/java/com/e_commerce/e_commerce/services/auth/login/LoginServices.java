package com.e_commerce.e_commerce.services.auth.login;

import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class LoginServices {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private SecurityHelper securityHelper;

    public User login(String email, String password){
        User user =userRepository.findByEmail(email);
        
        if (securityHelper.checkHashEquality(password, user.getPassword())) {
            return user;
        }
        else{
            return null;
        }
    }
}
