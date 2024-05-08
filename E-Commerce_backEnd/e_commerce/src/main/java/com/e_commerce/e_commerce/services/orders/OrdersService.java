package com.e_commerce.e_commerce.services.orders;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.models.Orders;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrdersService {

    @Autowired
    private final OrdersModel ordersModel;
    private final ResponseHelper responseHelper;
    private final SecurityHelper securityHelper;

    public ResponseEntity<Object> getUserOrdersServ(@RequestBody Map<String, Object> data) {
        try {
            if (Boolean.TRUE.equals(securityHelper.checkUserCredantials(data))) {
                int userID = (int) data.get("userId");
                try {
                    return responseHelper.createSuccessResponse("Orders Loaded Successfully",
                            ordersModel.getUserOrders(userID));
                } catch (Exception e) {
                    throw new Exception("Error Loading Orders: ");
                }

            } else {
                throw new Exception("Unauthorized Access ! ");
            }
        } catch (Exception e){
            throw new RuntimeException("Error while get user orders : "+ e.getMessage());
        }

    }

    public ResponseEntity<Object> updateOrderServ(@RequestBody Map<String, Object> order) {
        try {
            int orderId = (int) order.get("orderId");
            String newStatus = (String) order.get("newStatus");
            System.out.println("11111111111111");
            if(Boolean.TRUE.equals(ordersModel.updateOrderStatus(orderId, newStatus))){
                System.out.println("2222222222222");
                return responseHelper.createSuccessResponse("order updated Successfully", null);
            }
            throw new Exception("Error while adding order in database !");

        } catch (Exception e){
            throw new RuntimeException("Error while update order status !");
        }
    }

    public ResponseEntity<Object> addOrderServ(@RequestBody List<Map<String, Object>> data) {

        try {
            for (Map<String,Object> element : data)
                if (!Boolean.TRUE.equals(securityHelper.checkUserCredantials(element))){
                    throw new Exception("Unauthorized Access ! ");
                }

            List<Orders> dataForTheModel = new ArrayList<>();

            for (Map<String, Object> order : data) {
                Orders newOrder = new Orders(order); // Assuming you have a constructor in Orders class
                dataForTheModel.add(newOrder);
            }

            ordersModel.addOrder(dataForTheModel);
            return responseHelper.createSuccessResponse("Orders Added Successfully", null);

        } catch (Exception e) {
            throw  new RuntimeException("Error while adding order ");
        }
    }

    public ResponseEntity<Object> viewAllOrdersServ() {
        try {
            if(!ordersModel.getAllOrders().isEmpty())
                return responseHelper.createSuccessResponse("orders loaded successfully", ordersModel.getAllOrders());
            throw new Exception("There is no orders");

        } catch (Exception e){
            throw new RuntimeException("Error while view all orders ");
        }

    }
}
