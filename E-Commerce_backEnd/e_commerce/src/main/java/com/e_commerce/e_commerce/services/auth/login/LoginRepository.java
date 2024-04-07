package com.e_commerce.e_commerce.services.auth.login;

import com.e_commerce.e_commerce.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.e_commerce.models.User;

@Repository
public interface LoginRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
    User findByEmailAndPassword(String email, String password);
}
