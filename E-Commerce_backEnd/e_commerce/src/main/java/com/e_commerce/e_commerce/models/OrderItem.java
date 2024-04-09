package com.e_commerce.e_commerce.models;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItem {
    private int userId;
    private int productId;
    private int quantity;
    private int price;
    public OrderItem(){

    }
    public OrderItem(Map<String, Object> data){
        this.userId = (int) data.get("userId");
        this.productId = (int) data.get("productId");
        this.quantity = (int) data.get("quantity");
        this.price = (int) data.get("price");
    }
    public Map<Object, Object> toMap() {
        Map<Object, Object> data = new LinkedHashMap<>();
        data.put("userId", userId);
        data.put("productId", productId);
        data.put("quantity", quantity);
        data.put("price", price);
        return data;
    }


    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }


    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }




}
