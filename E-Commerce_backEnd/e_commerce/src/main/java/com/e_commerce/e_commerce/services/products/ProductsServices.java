package com.e_commerce.e_commerce.services.products;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e_commerce.e_commerce.models.Products;
@Service
public class ProductsServices {
    @Autowired
    private ProductsModel productsModel;
    public List<Products> getAllProducts() {
        return productsModel.getAllProducts();
    }
    public List<Products> searchForProduct(String keyword){
        return productsModel.searchForProduct(keyword);
    }
    public Products getProduct(int productId){
        return productsModel.getProduct(productId);
    }
    public Boolean addProduct(Map<String,Object> data){
        try {
            productsModel.addProducts(data);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
