package com.example.inventory.services;

import org.springframework.http.ResponseEntity;

import com.example.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	public ResponseEntity<CategoryResponseRest> search();

}
