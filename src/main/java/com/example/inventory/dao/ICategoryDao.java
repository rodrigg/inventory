package com.example.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.inventory.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long>{

}
