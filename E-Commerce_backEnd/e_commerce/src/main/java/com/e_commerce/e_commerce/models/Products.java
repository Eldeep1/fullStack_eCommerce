package com.e_commerce.e_commerce.models;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
