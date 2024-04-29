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
import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.models.Orders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/market/Orders")
public class OrdersController {
    @Autowired
    OrdersModel ordersModel;
    private ResponseHelper responseHelper = new ResponseHelper();
    @Autowired
    private SecurityHelper securityHelper;

    @PostMapping("")
    public ResponseEntity<Object> getUserOrders(@RequestBody Map<String, Object> data) {
        if (Boolean.TRUE.equals(securityHelper.checkUserCredantials(data))) {

            int userID = (int) data.get("userId");
            try {
                return responseHelper.createSuccessResponse("Orders Loaded Successfully",
                        ordersModel.getUserOrders(userID));
            } catch (Exception e) {
                return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error Loading Orders: " ,
                        null);
            }
        } else {
            return responseHelper.createUnauthorizedResponse();
        }
    }
     @GetMapping("/viewAll")
    public ResponseEntity<Object> getMethodName() {

        return responseHelper.createSuccessResponse("orders loaded successfully", ordersModel.getAllOrders());
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<Object> deleteOrder(@RequestBody Map<String, Object> order) {

        int orderId = (int) order.get("orderId");
        String newStatus = (String) order.get("newStatus");

        return Boolean.TRUE.equals(ordersModel.updateOrderStatus(orderId, newStatus))
                ? responseHelper.createSuccessResponse("order updated Successfully", null)
                : responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "an error happened", null);

    }

    @JsonIgnoreProperties
    @PostMapping("/add")
    public ResponseEntity<Object> addOrder(@RequestBody List<Map<String, Object>> data) {

        for (Map<String,Object> element : data) {
            if (!Boolean.TRUE.equals(securityHelper.checkUserCredantials(element))){
                return responseHelper.createUnauthorizedResponse();
            }
        }


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


