package com.e_commerce.e_commerce.services.products;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
}
