package com.e_commerce.e_commerce.models;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="user")
public class User implements UserDetails {
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
    
    public enum Role {
        USER,
        ADMIN
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
