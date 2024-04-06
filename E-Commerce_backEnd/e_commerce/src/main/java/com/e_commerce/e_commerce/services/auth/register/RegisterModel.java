package com.e_commerce.e_commerce.services.auth.register;


import com.e_commerce.e_commerce.models.User;

public class RegisterModel {
    RegisterRepository registerRepository=new RegisterRepository();
    
    public  String createUser(String firstName, String lastName,String userName,String email, String password) {
        if(Boolean.TRUE.equals(checkForEmail(email))){
            return "used Email";
        }
        else{
            //there should be a functino that generates the token here ! 
            //but for now, let's assume that the token is fixed...
            User user= new User("123456ssdfa23", firstName, lastName, userName, email, password);
            return registerRepository.setUser(user)?"created successfully":"database error";
        }
        
    }


    private  Boolean  checkForEmail(String email) {

        return registerRepository.checkForUsedEmails(email);
    }

}
