package com.e_commerce.e_commerce.services.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_commerce.e_commerce.models.Orders;
import com.e_commerce.e_commerce.repositories.OrderRepository;

@Component
public class OrdersModel {
    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getUserOrders(int userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    public Boolean updateOrderStatus(int orderId,String newStatus) {
        try {
             orderRepository.updateOrderStatus(newStatus,orderId);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public List<Orders> getAllOrders(){
        return (List<Orders>) orderRepository.findAll();
    }

    public Boolean addOrder(List<Orders> order) {
        try {
            orderRepository.saveAll(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}