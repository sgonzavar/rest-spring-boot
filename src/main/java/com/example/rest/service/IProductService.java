package com.example.rest.service;

import com.example.rest.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void deleteById(Long id);

    List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
}
