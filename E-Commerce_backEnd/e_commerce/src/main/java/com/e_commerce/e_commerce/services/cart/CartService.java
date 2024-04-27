package com.e_commerce.e_commerce.services.cart;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.models.Cart;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@AllArgsConstructor
public class CartService {

    @Autowired
    private final ResponseHelper responseHelper;
    private final SecurityHelper securityHelper;
    private final CartModel cartModel;

    public ResponseEntity<Object> viewCartItemsServ(@RequestBody Map<String, Object> data) {
        if (Boolean.TRUE.equals(securityHelper.checkUserCredantials(data))) {
            int userId = (int) data.get("userId");

            try {
                return responseHelper.createSuccessResponse("cart loaded Successfully", cartModel.getUsersCart(userId));
            } catch (Exception e) {
                return responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "couldn't get the cart items", null);
            }
        } else {
            return responseHelper.createUnauthorizedResponse();
        }
    }

    public ResponseEntity<Object> addCartItemsServ(@RequestBody Map<String, Object> cartAsMap) {
        Cart cart = new Cart(cartAsMap);

        if (Boolean.TRUE.equals(securityHelper.checkUserCredantials(cartAsMap))) {

            return Boolean.TRUE.equals(cartModel.addToCart(cart))
                    ? responseHelper.createSuccessResponse("item added Succssfully", cart)
                    : responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "failed adding the item", null);
        } else {
            return responseHelper.createUnauthorizedResponse();
        }
    }

    public ResponseEntity<Object> removeCartFromItemsServ(@RequestBody Map<String, Object> data) {
        if (Boolean.TRUE.equals(securityHelper.checkUserCredantials(data))) {
            System.out.println("we are fucking here");
            if(Boolean.FALSE.equals(securityHelper.checkCartOwner(data)))
                return responseHelper.createUnauthorizedResponse();
            int cartId = (int) data.get("cartId");


            return Boolean.TRUE.equals(cartModel.removeFromCart(cartId))
                    ? responseHelper.createSuccessResponse("item removed Succssfully", null)
                    : responseHelper.createErrorResponse(HttpStatus.UNAUTHORIZED, "failed adding the item", null);
        } else
            return responseHelper.createUnauthorizedResponse();
    }
}
