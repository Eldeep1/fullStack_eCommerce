package com.e_commerce.e_commerce.services.auth.register;


import com.e_commerce.e_commerce.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.e_commerce.e_commerce.models.User;

/**
 * RegisterRepository
 */
public interface RegisterRepository extends CrudRepository<User,Integer>{

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
    
}