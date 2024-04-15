package com.e_commerce.e_commerce.models;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private int cartId;
    @Embedded
    private OrderItem orderItem;
    
    public Cart(){
        orderItem=new OrderItem();
    }
    public Cart(Map<String,Object> data){
        this.orderItem=new OrderItem(data);
    }
    public Map<Object, Object> toMap() {
        Map<Object, Object> data = new LinkedHashMap<>();
        data.put("cartId", cartId);
        data.putAll(orderItem.toMap());
        return data;
    }

    public int cartId() {
        return cartId;
    }
     public int getUserId() {
        return orderItem.getUserId();
    }

    public int getProductId() {
        return  orderItem.getProductId();
    }


    public int getQuantity() {
        return orderItem.getQuantity();
    }

    public int getPrice() {
        return orderItem.getPrice();
    }


}
