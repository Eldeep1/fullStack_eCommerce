package com.e_commerce.e_commerce.services.auth.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.e_commerce.models.User;
@Service
public class RegisterServices {
    @Autowired
    RegisterModel registerModel;

    public  String signUp(User user){
        
        return registerModel.createUser(user);
    }
}
