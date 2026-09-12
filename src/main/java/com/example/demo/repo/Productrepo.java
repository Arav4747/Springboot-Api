package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface Productrepo extends JpaRepository<Product, Integer> {
public List<Product> findByCategory(String category);
public List<Product> findByPriceBetween(double min ,double max);
}
