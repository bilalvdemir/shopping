package com.example.shopping.shopping.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.shopping.shopping.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
