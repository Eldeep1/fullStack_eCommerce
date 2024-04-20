package com.e_commerce.e_commerce.repositories;

import com.e_commerce.e_commerce.models.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
    User findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.password = ?2 WHERE u.email = ?1")
    public Boolean resetPassword(String email, String newPassword);
    
}