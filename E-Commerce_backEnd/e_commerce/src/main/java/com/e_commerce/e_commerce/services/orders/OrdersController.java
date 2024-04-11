package com.e_commerce.e_commerce.services.orders;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.models.Orders;

import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/Orders/add")
    public ResponseEntity<Object> addOrder(@RequestBody Map<String, Object> orderAsMap) {
        Orders order=new Orders(orderAsMap);
        return ordersModel.addOrder(order)?
        responseHelper.createSuccessResponse("order Added Successfully", null)
        :responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "an error happened", null);
    }
    

}