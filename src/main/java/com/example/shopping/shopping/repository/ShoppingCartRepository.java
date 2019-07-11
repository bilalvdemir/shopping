package com.example.shopping.shopping.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.shopping.shopping.model.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {

}
