package com.e_commerce.e_commerce.services.products;

import java.util.List;

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
}
