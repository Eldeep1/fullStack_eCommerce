package com.e_commerce.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.e_commerce.models.Products;

@Repository
public interface ProductRepository extends CrudRepository<Products, Integer> {
    @Query("SELECT u FROM Products u WHERE u.productName LIKE %?1%")
    List<Products> searchForProducts(String keyword); 
}
