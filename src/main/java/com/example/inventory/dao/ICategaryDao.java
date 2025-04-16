package com.example.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.inventory.model.Category;

public interface ICategaryDao extends CrudRepository<Category, Long>{

}
