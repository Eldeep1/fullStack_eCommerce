package com.e_commerce.e_commerce.models;

import java.util.LinkedHashMap;
import java.util.Map;

import com.e_commerce.e_commerce.validation.Validation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    private String userName;
    private String email;
    
    private String token;

    private String password;

    public User( String token,String firstName, String lastName,String userName,String email, String password) {
        this.firstName = firstName;
        this.token = token;
        this.lastName=lastName;
        this.email=email;
        this.userName=userName;
        this.password=password;
    }
    
    public User(){}
    public User(Integer id, String token,String firstName, String lastName,String userName,String email){
        this.firstName = firstName;
        this.token = token;
        this.lastName=lastName;
        this.email=email;
        this.userName=userName;
        this.id=id;
    }

   
public Map<Object, Object> userToMap() {
    Map<Object, Object> data = new LinkedHashMap<>(); 

    // Add user properties to the map
    data.put("id", id);
    data.put("firstName", firstName);
    data.put("lastName", lastName);
    data.put("token", token);
    data.put("email", email);
    data.put("userName", userName);

    return data;
}
    public String getToken() {
        return this.token;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
    public String getUserName() {
        return this.userName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }

    public Integer getId() {
        return this.id;
    }
    public Map<String,String> Validate(){

        Validation validation= new Validation();
        Map<String,String> errors= new LinkedHashMap<>();
    
        if(!validation.validateFirstName(firstName)){
            errors.put("firstname","Firstname must contain at least 3 characters");
        }
        if(!validation.validateLastName(lastName)){
            errors.put("lastname","Lastname must contain at least 3 characters");
        }
        if (!validation.validateUserName(userName)) {
            errors.put("username","Username must contain at least 3 characters and a maximum of 20 characters");    
        }
        if(!validation.validateEmail(email)){
            errors.put("email","The email must be in correct format");
        }
        if(!validation.validatePassword(password)){
            errors.put("password","password must contain at least 4 characters and a maximum of 10 characters");
        }
        return errors;
    }
}
