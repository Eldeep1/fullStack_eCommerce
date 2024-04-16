package com.e_commerce.e_commerce.validation;

public class Validation {
    
    public Validation(){

    }
    public boolean validateFirstName(String firstname){

        if(firstname != null && firstname.length()>=3){

            return true; 
        }
        else{
            return false;
        }
    }
    public boolean validateLastName(String lastname){
        if(lastname != null && lastname.length()>=3){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean validateEmail(String email){

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if(email != null && email.matches(emailRegex)){

            return true;
        }
        return false;
    }
    public boolean validatePassword(String password){

        if(password !=null && (password.length()>=4 && password.length()<=10) ){
            
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
