package com.e_commerce.e_commerce.services.auth.register;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.repositories.UserRepository;
@Component
public class RegisterModel {
    @Autowired
    UserRepository userRepository;

    
    public  String createUser(String firstName, String lastName,String userName,String email, String password) {
        if(Boolean.TRUE.equals(checkForEmail(email))){
            return "used Email";
        }
        else{
            //there should be a functino that generates the token here ! 
            //but for now, let's assume that the token is fixed...
            User user= new User("123456ssdfa23", firstName, lastName, userName, email, password);
            try {
                userRepository.save(user);
                return "created successfully";
            } catch (DataAccessException e) {
                return "database error";
            }
        }
        
    }

    private Boolean checkForEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user==null) {
return false;       
 }
    return true;
    }

}
