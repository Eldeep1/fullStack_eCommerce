package com.e_commerce.e_commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.e_commerce.models.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart,Integer>{
    @Query("select c from Cart c where c.orderItem.userId = ?1")
    public List<Cart> getUsersCarts(int userId);
}
