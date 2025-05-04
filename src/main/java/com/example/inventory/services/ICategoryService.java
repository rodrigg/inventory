package com.example.inventory.services;

import org.springframework.http.ResponseEntity;

import com.example.inventory.model.Category;
import com.example.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	public ResponseEntity<CategoryResponseRest> search();


	public ResponseEntity<CategoryResponseRest> searchByUd(Long id);
	
	
	public ResponseEntity<CategoryResponseRest> save(Category category);


	public ResponseEntity<CategoryResponseRest> actualizar(Category category,Long id);
	
	
	public ResponseEntity<CategoryResponseRest> deleteById(Long id);


}
