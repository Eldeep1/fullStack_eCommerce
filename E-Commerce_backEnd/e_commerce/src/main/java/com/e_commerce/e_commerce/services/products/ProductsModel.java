package com.e_commerce.e_commerce.services.products;

import java.util.List;

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
        return productRepository.searchForProducts(keyword);
    }
}
