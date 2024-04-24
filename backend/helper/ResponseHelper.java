package com.e_commerce.e_commerce.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHelper {

    private static final boolean SUCCESS_STATUS = true;
    private static final boolean ERROR_STATUS = false;


    //we used linked hash map instead of normal hash map so that the api answer be on a specific order
    private  Map<String, Object> generateResponse(boolean status, String message, Object data) {
        Map<String, Object> responseMap = new LinkedHashMap<>(); // Use LinkedHashMap to maintain order
        responseMap.put("status", status);
        responseMap.put("message", message);
        responseMap.put("data", data != null ? data : null);
        return responseMap;
    }

    public  ResponseEntity<Object> createSuccessResponse(String message, Object data) {
        return ResponseEntity.ok().body(generateResponse(SUCCESS_STATUS, message, data));
    }

    public  ResponseEntity<Object> createErrorResponse(HttpStatus status, String message, Object data) {
        return ResponseEntity.status(status).body(generateResponse(ERROR_STATUS, message, data));
    }
}
