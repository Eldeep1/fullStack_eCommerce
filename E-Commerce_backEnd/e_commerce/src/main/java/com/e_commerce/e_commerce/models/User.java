package com.e_commerce.e_commerce.models;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.Column;
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
    @Column(nullable = false) 
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String authQuestionAnswer;
    @Column(nullable = false)
    private String authQuestionId;

    public User( String token,String firstName, String lastName,String userName,String email, String password,String authQuestionAnswer,String questionId) {
        this.firstName = firstName;
        this.token = token;
        this.lastName=lastName;
        this.email=email;
        this.userName=userName;
        this.password=password;
        this.authQuestionAnswer=authQuestionAnswer;
        this.authQuestionId=questionId;
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
    
    public String getAuthQuestionAnswer() {
        return this.authQuestionAnswer;
    }
    
    public String getQuestionId() {
        return this.authQuestionId;
    }
}
