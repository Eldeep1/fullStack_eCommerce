package com.e_commerce.e_commerce.services.auth.signup;


import com.e_commerce.e_commerce.helper.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.e_commerce.e_commerce.models.User;
import com.e_commerce.e_commerce.repositories.UserRepository;

import java.util.Map;

@Component
public class RegisterModel {
    @Autowired
    UserRepository userRepository;
    private  SecurityHelper securityHelper;
    
    public  String createUser(User user) {
        try {
            if(Boolean.TRUE.equals(checkForEmail(user.getEmail()))){
                throw new Exception("Used email ");
            }
            else{
                //there should be a functino that generates the token here !
                //but for now, let's assume that the token is fixed...
                // User user= new User("123456ssdfa23", firstName, lastName, userName, email, password,questinoAnswer,questionId);
                userRepository.save(user);
                return "created successfully";
            }

        } catch (Exception e){
            throw new RuntimeException("Error while adding user ! " + e.getMessage());
        }
    }

    private Boolean checkForEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user==null)
            return false;
        return true;
    }

    public User hashedCredentials (Map<String,Object> credentials){

        String hashedPassword = (String) credentials.get("password");
        hashedPassword = securityHelper.hashString(hashedPassword);

        credentials.put("password", hashedPassword);
        credentials.put("role", credentials.get("role"));

        String hashedAnswer = (String) credentials.get("questinoAnswer");
        hashedAnswer = securityHelper.hashString(hashedAnswer);
        credentials.put("questinoAnswer", hashedAnswer);
        User user = new User(credentials);
        return user;
    }

    public  String signUp(User user){
        return createUser(user);
    }

}
