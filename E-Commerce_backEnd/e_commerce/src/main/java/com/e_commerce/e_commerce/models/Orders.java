package com.e_commerce.e_commerce.models;

import java.util.Date;
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
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Embedded
    private OrderItem orderItem;
    private String orderStatus;
    private String checkOutMethod;
    private Date orderDate;

    public Orders(){
        orderItem = new OrderItem();
    }

    public Orders(Map<String, Object> data) {
        this.orderStatus = (String) data.get("orderStatus");
        this.orderItem=new OrderItem(data);
        this.checkOutMethod= (String) data.get("checkOutMethod");
        this.orderDate = new Date();

    }
    public Map<Object, Object> toMap() {
        Map<Object, Object> data = new LinkedHashMap<>();
        data.put("orderId", orderId);
        data.put("orderStatus", orderStatus);
        data.putAll(orderItem.toMap());
        data.put("checkOutMethod",checkOutMethod);
        data.put("orderDate", orderDate);

        return data;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public int getOrderId() {
        return orderId;
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

    public Date getDate() {
        return orderDate;
    }

}
