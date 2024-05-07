package com.e_commerce.e_commerce.services.products;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("market/products")
@AllArgsConstructor
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("")
    public ResponseEntity<Object> getAllProducts() {
        return productsService.getAllProductsServ();
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchForProducts(@RequestParam("q") String searchQuery) {
        return productsService.searchForProductsServ(searchQuery);
    }

    @GetMapping("/oneProduct")
    public ResponseEntity<Object> getProductById(@RequestParam("pID") int productId) {
        return productsService.getProductByIdServ(productId);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Object> addProduct(@RequestBody Map<String,Object> data) {
        return productsService.addProductServ(data);
    }

}
