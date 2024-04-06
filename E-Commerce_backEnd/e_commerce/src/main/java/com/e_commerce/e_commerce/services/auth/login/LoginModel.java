package com.e_commerce.e_commerce.services.auth.login;

import com.e_commerce.e_commerce.models.User;

public class LoginModel {
    private LoginRepository loginRepository=new LoginRepository(); 

    public  User login(String email, String password) {
        return loginRepository.checkForCredentials(email, password);
    }
}
