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
public class User implements UserDetails{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false) 
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String authQuestionAnswer;
    @Column(nullable = false)
    private String authQuestionId;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Integer getId() {
        return id;
    }

    public User(){}

    public User(Map<String,Object> data) {
        this.firstName = (String)data.get("firstName");
        this.lastName=(String)data.get("lastName");
        this.email=(String) data.get("email");
        this.userName=(String) data.get("userName");
        this.password=(String) data.get("password");
        this.authQuestionAnswer=(String) data.get("questinoAnswer");
        this.authQuestionId=(String) data.get("questinoId");
        this.role=(Role) data.get("role");
    }

    
public Map<Object, Object> userToMap() {
    Map<Object, Object> data = new LinkedHashMap<>(); 

    // Add user properties to the map
    data.put("id", id);
    data.put("firstName", firstName);
    data.put("lastName", lastName);
    data.put("email", email);
    data.put("userName", userName);
    data.put("Role",role);

    return data;
}


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }


    public Role getRole() {
        return role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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

    
    
    
    public String getAuthQuestionAnswer() {
        return this.authQuestionAnswer;
    }
    
    public String getQuestionId() {
        return this.authQuestionId;
    }

}
