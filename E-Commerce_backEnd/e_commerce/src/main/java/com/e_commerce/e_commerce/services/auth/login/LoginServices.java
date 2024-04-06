package com.e_commerce.e_commerce.services.auth.login;

import com.e_commerce.e_commerce.models.User;

public class LoginServices {
    private LoginModel loginModel=new LoginModel(); 

    public User login(String email, String password){
        return loginModel.login(email,password);

    }
}
