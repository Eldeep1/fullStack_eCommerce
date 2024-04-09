package com.e_commerce.e_commerce.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.e_commerce.models.Orders;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer> {
    // Select all orders for a given user ID
    @Query("SELECT o FROM Orders o WHERE o.orderItem.userId = ?1")
    public List<Orders> findOrdersByUserId(int userId);
    
    @Query("select o from Orders o where o.orderStatus='in cart'")
    public List<Orders> addToCart(int userId);

    @Modifying
    @Query("UPDATE Orders o SET o.orderStatus = ?1 WHERE o.orderId = ?2")
    @Transactional  
    public void updateOrderStatus(String newStatus, int orderID);
    
    
}
