package com.e_commerce.e_commerce.services.auth.register;

public class RegisterServices {
    RegisterModel registerModel=new RegisterModel();

    public  String signUp(String firstName, String lastName,String userName,String email, String password){
        
        return registerModel.createUser(firstName,lastName,userName,email,password);
    }
}
