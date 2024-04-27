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

    public ResponseEntity<Object> updateOrderServ(@RequestBody Map<String, Object> order) {

        int orderId = (int) order.get("orderId");
        String newStatus = (String) order.get("newStatus");

        return Boolean.TRUE.equals(ordersModel.updateOrderStatus(orderId, newStatus))
                ? responseHelper.createSuccessResponse("order updated Successfully", null)
                : responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "an error happened", null);

    }

    public ResponseEntity<Object> addOrderServ(@RequestBody List<Map<String, Object>> data) {

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
