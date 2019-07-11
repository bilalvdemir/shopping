package com.example.shopping.shopping.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.shopping.shopping.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    public Category findById(String id);

}
