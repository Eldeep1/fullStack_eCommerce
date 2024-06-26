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
        try {
                int userId = (int) data.get("userId");
                if(cartModel.getUsersCart(userId).isEmpty()){
                    throw new Exception("There is no carts for this user !");
                }
                return responseHelper.createSuccessResponse("cart loaded Successfully", cartModel.getUsersCart(userId));

        } catch (Exception e){
            throw new RuntimeException("Error While view cart items !"+e.getMessage());
        }

    }

    public ResponseEntity<Object> addCartItemsServ(@RequestBody Map<String, Object> cartAsMap) {
        try {
            Cart cart = new Cart(cartAsMap);
            System.out.println(Boolean.TRUE.equals(cartModel.addToCart(cart)));
            if(Boolean.TRUE.equals(cartModel.addToCart(cart)))
                return responseHelper.createSuccessResponse("item added Successfully", cart);
            throw new Exception("Internal Error : Can't add the cart item to database !");

        } catch (Exception e){
            throw new RuntimeException("Error While adding cart items ! ");
        }
    }

    public ResponseEntity<Object> removeCartFromItemsServ(@RequestBody Map<String, Object> data) {
        try {
                if(Boolean.FALSE.equals(securityHelper.checkCartOwner(data)))
                    throw new Exception("Unauthorized Access ! ");
                int cartId = (int) data.get("cartId");
                System.out.println("We are here 1 ");
                if(Boolean.TRUE.equals(cartModel.removeFromCart(cartId))){
                    System.out.println("We are here 2 ");
                    return responseHelper.createSuccessResponse("item removed Successfully", null);
                }
                throw new Exception("Internal Error : Can't remove the item from the database !");
        } catch (Exception e){
            throw new RuntimeException("Error While remove cart item ! ");
        }
    }
}
