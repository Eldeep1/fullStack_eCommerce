package com.e_commerce.e_commerce.models;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private String productPrice;
    private String productDescription;
    private String porductImage;

    public Products() {
    }

    public Map<Object, Object> productToMap() {
        Map<Object, Object> data = new LinkedHashMap<>();

        // Add user properties to the map
        data.put("id", id);
        data.put("productName", productName);
        data.put("productPrice", productPrice);
        data.put("productDescription", productDescription);
        data.put("porductImage", porductImage);

        return data;
    }

    public Integer getID() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductPrice() {
        return this.productPrice;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public String getProductImage() {
        return this.porductImage;
    }
}
