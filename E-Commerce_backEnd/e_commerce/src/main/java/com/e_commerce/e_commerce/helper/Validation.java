package com.e_commerce.e_commerce.helper;

import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

//import com.e_commerce.e_commerce.models.User;


@Configuration
public class Validation {
    
    public Validation(){

    }

    public Map<String,String> validateSignup(Map<String, Object> credantials){

        Map<String,String> errors= new LinkedHashMap<>();
        if(!validateFirstName((String)credantials.get("firstName"))){
            errors.put("firstname","Firstname must contain at least 3 characters");
        }
        if(!validateLastName((String)credantials.get("lastName"))){
            errors.put("lastname","Lastname must contain at least 3 characters");
        }
        if (!validateUserName((String)credantials.get("userName"))) {
            errors.put("username","Username must contain at least 3 characters and a maximum of 20 characters");    
        }
        if(!validateEmail((String)credantials.get("email"))){
            errors.put("email","The email must be in correct format");
        }
         if(!validatePassword((String)credantials.get("password"))){
            errors.put("password","Password must contain exactly one capiltal letter and least two digits, with a length between 4 and 10 characters");
        }
        if(!validateQuestionAnswer((String)credantials.get("questinoAnswer"))){
            errors.put("QuestionAnswer","QuestionAnswer must contain at least 3 characters and not end with ' and <>");
        }
        
        return errors;
    }

    public Map<String,String> validateLogin(String email){

        Map<String,String> errors= new LinkedHashMap<>();
        if(!validateEmailLogin(email)){
            errors.put("Email","Please enter email in correct format");
        }
        return errors;

    }
    private boolean validateFirstName(String firstname){

        if(firstname != null && firstname.length()>=3){

            return true; 
        }
        return false;   
    }
    private boolean validateLastName(String lastname){
        if(lastname != null && lastname.length()>=3){
            return true;
        }
        return false;
    }
    private boolean validateEmail(String email){

        String emailRegex = "^.+@.+\\.com$";   // Here we need the right expression

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
    private boolean validateUserName(String username){

        if(username !=null && (username.length()>=3 && username.length()<=20)){

            return true;
        }
        return false;
    }
    private boolean validateEmailLogin(String email){

        String emailregex = "^.+@.+\\.com$";

        if(email.matches(emailregex)){
            return true;
        }
        return false;
    }
    private boolean validateQuestionAnswer(String answer){

        if(answer.length()<3 || answer.endsWith("'") || answer.endsWith("\"") || answer.endsWith("<>")){
            return false;
        }
        return true;

    }
}