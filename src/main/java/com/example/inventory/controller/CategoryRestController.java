package com.example.inventory.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory.model.Category;
import com.example.inventory.response.CategoryResponseRest;
import com.example.inventory.response.ProductResponseRest;
import com.example.inventory.services.ICategoryService;
import com.example.inventory.utils.CategoryExcelExporter;
import com.example.inventory.utils.ProductExcelExporter;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService service;
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories(){
		
		ResponseEntity<CategoryResponseRest> response = service.search();
	
		return response;
			
	}
	
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id){
		
		ResponseEntity<CategoryResponseRest> response = service.searchByUd(id);
	
		return response;
			
	}
	
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> guardarCategory(@RequestBody Category category){
		
		ResponseEntity<CategoryResponseRest> response = service.save(category);
	
		return response;
			
	}
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> actualizarCategory(@PathVariable Long id,@RequestBody Category category){
		
		ResponseEntity<CategoryResponseRest> response = service.actualizar(category,id);
	
		return response;
			
	}
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> deleteCategory(@PathVariable Long id){
		
		ResponseEntity<CategoryResponseRest> response = service.deleteById(id);
	
		return response;
			
	}
	@GetMapping("/categories/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=result_product.xlsx";
		response.setHeader(headerKey, headerValue);
		
		ResponseEntity<CategoryResponseRest> category = service.search();
		
		CategoryExcelExporter excelExporter = new CategoryExcelExporter(
				category.getBody().getCategoryResponse().getCategory());
		
		excelExporter.export(response);
				
		
	}
}
