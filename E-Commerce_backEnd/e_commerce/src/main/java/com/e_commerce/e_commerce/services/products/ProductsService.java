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
            List<Products> productsList =productsModel.getAllProducts();
            if(productsList.isEmpty()){
                throw new Exception("There are no products ");
            }
            return responseHelper.createSuccessResponse("Products Loaded Successfully",productsList);

        } catch (Exception e) {
            throw new RuntimeException("Error Loading Products , "+e.getMessage());
        }
    }

    public ResponseEntity<Object> searchForProductsServ(@RequestParam("q") String searchQuery) {
        try {
            List<Products> products = productsModel.searchForProduct(searchQuery);
            if(products.isEmpty())
                throw new Exception("Product name doesn't exist , Tyr again !");
            return responseHelper.createSuccessResponse("Search done successfully ",products);
        } catch (Exception e) {
            throw new RuntimeException("Error while getting the product, "+e.getMessage());
        }
    }

    public ResponseEntity<Object> getProductByIdServ(@RequestParam("pID") int productId) {
        try {
            Products product = productsModel.getProduct(productId);
            if (product == null)
                throw new Exception("Product Id doesn't exist , Tyr again !");
            return responseHelper.createSuccessResponse("product got successfully", product.productToMap());
        } catch (Exception e) {
            throw new RuntimeException("Error while getting the product, "+e.getMessage());
        }
    }


    public List<Products> searchForProduct(String keyword){
        try {
            List<Products> product = productsModel.searchForProduct(keyword);
            if(product.isEmpty())
                throw new Exception("Product name doesn't exist , Tyr again !");
            return product;
        } catch (Exception e) {
            throw new RuntimeException("Error while getting the product, "+e.getMessage());
        }
    }


}
