package com.e_commerce.e_commerce.services.products;

import java.util.List;

import com.e_commerce.e_commerce.helper.ResponseHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.e_commerce.e_commerce.models.Products;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@AllArgsConstructor
public class ProductsService {
    @Autowired
    private ProductsModel productsModel;
    private ResponseHelper responseHelper;


    public ResponseEntity<Object> getAllProductsServ() {
        try {
            return responseHelper.createSuccessResponse("Products Loaded Successfully",getAllProducts());

        } catch (Exception e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error Loading Products \n "+e,null);

        }
    }

    public ResponseEntity<Object> searchForProductsServ(@RequestParam("q") String searchQuery) {
        try {
            return responseHelper.createSuccessResponse("Search done successfully",searchForProduct(searchQuery));
        } catch (Exception e) {
            return responseHelper.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ""+ e, null);
        }
    }

    public ResponseEntity<Object> getProductByIdServ(@RequestParam("pID") int productId) {
        Products product = getProduct(productId);
        if (product != null) {
            return responseHelper.createSuccessResponse("product got succesfully", product.productToMap());
        } else {
            return responseHelper.createErrorResponse(HttpStatus.NOT_FOUND, "no such product", null);
        }
    }

    public List<Products> getAllProducts() {
        return productsModel.getAllProducts();
    }

    public List<Products> searchForProduct(String keyword){
        return productsModel.searchForProduct(keyword);
    }

    public Products getProduct(int productId){
        return productsModel.getProduct(productId);
    }



}
