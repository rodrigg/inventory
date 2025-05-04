package com.example.inventory.services;

import org.springframework.http.ResponseEntity;

import com.example.inventory.model.Product;
import com.example.inventory.response.ProductResponseRest;

public interface IProductService {

	public ResponseEntity<ProductResponseRest> save(Product product,Long categoryId);
	public ResponseEntity<ProductResponseRest> searchById(Long categoryId);
	public ResponseEntity<ProductResponseRest> searchByName(String name);
	public ResponseEntity<ProductResponseRest> deleteById(Long categoryId);
	public ResponseEntity<ProductResponseRest> search();
	public ResponseEntity<ProductResponseRest> update(Product product, Long categoryId, Long id);

}
