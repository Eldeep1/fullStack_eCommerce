package com.e_commerce.e_commerce.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import com.e_commerce.e_commerce.models.User;

public class Validation {
    
    public Validation(){

    }

    public Map<String,String> Validate(User user){

        Map<String,String> errors= new LinkedHashMap<>();
    
        if(!validateFirstName(user.getFirstName())){
            errors.put("firstname","Firstname must contain at least 3 characters");
        }
        if(!validateLastName(user.getLastName())){
            errors.put("lastname","Lastname must contain at least 3 characters");
        }
        if (!validateUserName(user.getUserName())) {
            errors.put("username","Username must contain at least 3 characters and a maximum of 20 characters");    
        }
        if(!validateEmail(user.getEmail())){
            errors.put("email","The email must be in correct format");
        }
        if(!validatePassword(user.getPassword())){
            errors.put("password","Password must contain exactly one capiltal letter and least two digits, with a length between 4 and 10 characters");
        }
        return errors;
    }
    public boolean validateFirstName(String firstname){

        if(firstname != null && firstname.length()>=3){

            return true; 
        }
        return false;   
    }
    public boolean validateLastName(String lastname){
        if(lastname != null && lastname.length()>=3){
            return true;
        }
        return false;
    }
    public boolean validateEmail(String email){

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if(email != null && email.matches(emailRegex)){

            return true;
        }
        return false;
    }
    public boolean validatePassword(String password){

        String passwordRegex="^(?=.*[A-Z])(?=.*\\d.*\\d)[a-zA-Z\\d]{4,10}$";

        if(password !=null && password.matches(passwordRegex)){
            
            return true;
        }
        return false;
    }
    public boolean validateUserName(String username){

        if(username !=null && (username.length()>=3 && username.length()<=20)){

            return true;
        }
        return false;
    }
}
