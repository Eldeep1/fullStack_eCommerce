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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private int cartId;
    @Embedded
    private OrderItem orderItem;
    private String productName;
    private String productImage;


    public Cart(Map<String,Object> data){
        this.orderItem=new OrderItem(data);
        this.productImage=(String)data.get("productImage");
        this.productName=(String)data.get("productName");

    }
    public Map<Object, Object> toMap() {
        Map<Object, Object> data = new LinkedHashMap<>();
        data.put("cartId", cartId);
        data.putAll(orderItem.toMap());
        data.put("productName",productName);
        data.put("productImage",productImage);
        return data;
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
