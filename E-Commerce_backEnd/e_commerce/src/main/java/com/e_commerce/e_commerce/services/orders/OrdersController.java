package com.e_commerce.e_commerce.services.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.models.Orders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/market/Orders")
@AllArgsConstructor
public class OrdersController {

    @Autowired
    private final OrdersService ordersService;

    @PostMapping("")
    public ResponseEntity<Object> getUserOrders(@RequestBody Map<String, Object> data) {
        return ordersService.getUserOrdersServ(data);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<Object> updateOrder(@RequestBody Map<String, Object> order) {
        return ordersService.updateOrderServ(order);
    }

    @JsonIgnoreProperties
    @PostMapping("/add")
    public ResponseEntity<Object> addOrder(@RequestBody List<Map<String, Object>> data) {
            return  ordersService.addOrderServ(data);
        } 
    }


