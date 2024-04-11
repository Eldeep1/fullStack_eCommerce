package com.e_commerce.e_commerce.services.cart;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.models.Cart;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("market")
public class CartController {
    @Autowired
     CartModel cartModel;
    ResponseHelper responseHelper=new ResponseHelper();
    @PostMapping("/cart/view")
    public ResponseEntity<Object> viewCartItems(@RequestBody Map<String, Object> data) {
        int userId=(int) data.get("userId");
        
        try {
            return responseHelper.createSuccessResponse("cart loaded Successfully", cartModel.getUsersCart(userId));
        } catch (Exception e) {
            return  responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "couldn't get the cart items",null);
                }
        
    }

    @PostMapping("cart/add")
    public ResponseEntity<Object> addCartItems(@RequestBody Map<String, Object> CartAsMap) {
        Cart cart = new Cart(CartAsMap);

        return cartModel.addToCart(cart)==true?
         responseHelper.createSuccessResponse("item added Succssfully", cart):
         responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "failed adding the item",null);
    }

    @PostMapping("cart/remove")
    public ResponseEntity<Object> removeCartFromItems(@RequestBody Map<String, Object> data) {
        int cartId=(int) data.get("cartId");
        
        return cartModel.removeFromCart(cartId)==true?
         responseHelper.createSuccessResponse("item removed Succssfully", null):
         responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "failed adding the item",null);
    }
    
    
    
}
