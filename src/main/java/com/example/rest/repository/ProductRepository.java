package com.example.rest.repository;

import com.example.rest.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    // sentencia que usa la notacion query
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findProductByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);


    //sentencia que usa querymethod
    // List<Product> findPRoductByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
