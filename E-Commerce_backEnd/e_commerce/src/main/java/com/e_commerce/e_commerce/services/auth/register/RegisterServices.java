package com.e_commerce.e_commerce.services.auth.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RegisterServices {
    @Autowired
    RegisterModel registerModel;

    public  String signUp(String firstName, String lastName,String userName,String email, String password,String questionId,String questinoAnswer){
        
        return registerModel.createUser(firstName,lastName,userName,email,password,questionId,questinoAnswer);
    }
}
