package com.e_commerce.e_commerce.services.cart;

import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import com.e_commerce.e_commerce.helper.SecurityHelper;
import com.e_commerce.e_commerce.models.Cart;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("market")
@AllArgsConstructor
public class CartController {

    @Autowired
    private final CartService cartService;

    @PostMapping("/cart/view")
    public ResponseEntity<Object> viewCartItems(@RequestBody Map<String, Object> data) {
        return cartService.viewCartItemsServ(data);
    }

    @PostMapping("cart/add")
    public ResponseEntity<Object> addCartItems(@RequestBody Map<String, Object> cartAsMap) {
        return cartService.addCartItemsServ(cartAsMap);
    }

    @PostMapping("cart/remove")
    public ResponseEntity<Object> removeCartFromItems(@RequestBody Map<String, Object> data) {
        return cartService.removeCartFromItemsServ(data);
    }

}
