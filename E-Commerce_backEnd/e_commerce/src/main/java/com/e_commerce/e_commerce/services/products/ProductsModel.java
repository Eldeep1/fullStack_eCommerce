package com.e_commerce.e_commerce.services.products;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_commerce.e_commerce.models.Products;
import com.e_commerce.e_commerce.repositories.ProductRepository;
@Component
public class ProductsModel {
    @Autowired
    private ProductRepository productRepository;

    public List<Products> getAllProducts() {
        return (List<Products>) productRepository.findAll();
    }

    public List<Products> searchForProduct(String keyword){
        if (keyword.isEmpty()){
            return Collections.emptyList();
        }
        return productRepository.searchForProducts(keyword);
    }

    public Products getProduct(int productId){
        Optional<Products> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null); // Or throw an exception if necessary
    }
    public Boolean addProducts(Map<String,Object> data){
        Products product = new Products(data);
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean addProduct(Map<String,Object> data){
        try {
            addProducts(data);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}