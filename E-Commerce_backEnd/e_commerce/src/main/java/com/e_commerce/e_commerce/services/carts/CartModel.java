package com.e_commerce.e_commerce.services.carts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.e_commerce.e_commerce.models.Cart;
import com.e_commerce.e_commerce.repository.CartRepository;

@Component
public class CartModel {
    
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getUsersCart(int userId){

        return cartRepository.getUsersCarts(userId);
    }
    public Boolean addToCart(Cart cart){
        try {
            cartRepository.save(cart);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removeFromCart(int cartId){
        try {
            cartRepository.deleteById(cartId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
