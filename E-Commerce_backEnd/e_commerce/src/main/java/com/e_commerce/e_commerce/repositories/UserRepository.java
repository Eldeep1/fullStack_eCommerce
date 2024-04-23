package com.e_commerce.e_commerce.repositories;

import com.e_commerce.e_commerce.models.User;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
    User findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = ?1 WHERE u.email = ?2")
    public int resetPassword(String newPassword, String email);

    
    @Query("SELECT u FROM User u WHERE u.authQuestionId = ?1 AND u.authQuestionAnswer = ?2 AND u.email = ?3")
    public User verifyUser(String questionId, String authQuestionAnswer, String email);
}