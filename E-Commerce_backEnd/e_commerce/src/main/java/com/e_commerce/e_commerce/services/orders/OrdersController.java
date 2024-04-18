package com.e_commerce.e_commerce.services.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.models.Orders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/market")
public class OrdersController {
    @Autowired
    OrdersModel ordersModel;
    private ResponseHelper responseHelper = new ResponseHelper();

    @PostMapping("/Orders")
    public ResponseEntity<Object> getUserOrders(@RequestBody Map<String, Integer> data) {
        int userID=data.get("userId");
        try {
            return responseHelper.createSuccessResponse("Orders Loaded Successfully",
                    ordersModel.getUserOrders(userID));
        } catch (Exception e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error Loading Orders: " + e,
                    null);
        }
    }

    @PostMapping("/Orders/updateStatus")
    public ResponseEntity<Object> deleteOrder(@RequestBody Map<String, Object> order) {

        int orderId = (int) order.get("orderId");
        String newStatus=(String) order.get("newStatus");
        
        return ordersModel.updateOrderStatus(orderId,newStatus)
                ? responseHelper.createSuccessResponse("order updated Successfully", null)
                : responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "an error happened", null);

    }

    @JsonIgnoreProperties
    @PostMapping("/Orders/add")
    public ResponseEntity<Object> addOrder(@RequestBody List<Map<String,Object>> data) {
        List<Orders> dataForTheModel = new ArrayList<>();
        try {
            for (Map<String, Object> order : data) {
                Orders newOrder = new Orders(order); // Assuming you have a constructor in Orders class
                dataForTheModel.add(newOrder);
            }
            ordersModel.addOrder(dataForTheModel);
            return responseHelper.createSuccessResponse("Orders Added Successfully", null);
        } catch (Exception e) {
            return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "An error occurred", null);
        }
    }
    

    }
    

