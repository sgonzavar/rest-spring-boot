package com.example.rest.dao.implement;

import com.example.rest.dao.IProductDAO;
import com.example.rest.entities.Product;
import com.example.rest.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ProductImplementDAO implements IProductDAO {

    @Autowired
    private IProductRepository IProductRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) IProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return IProductRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        IProductRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        IProductRepository.deleteById(id);
    }

    @Override
    public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return IProductRepository.findProductByPriceInRange(minPrice, maxPrice);
    }
}
