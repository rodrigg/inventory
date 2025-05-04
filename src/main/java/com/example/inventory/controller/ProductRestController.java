package com.example.inventory.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.inventory.model.Product;
import com.example.inventory.response.ProductResponseRest;
import com.example.inventory.services.IProductService;
import com.example.inventory.utils.ProductExcelExporter;
import com.example.inventory.utils.Utils;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
	@Autowired 
	private IProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> save(@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("account") int account,
			@RequestParam("categoryId") Long categoryID)throws IOException{
		
		
		Product product =new Product();
		product.setName(name);
		product.setAccount(account);
		product.setPrice(price);
		product.setPicture(Utils.compressZLib(picture.getBytes()));
		ResponseEntity<ProductResponseRest> response = productService.save(product,categoryID);

		return response;
			
	}
	
	@PostMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> save(@PathVariable Long id){
		
		ResponseEntity<ProductResponseRest> response = productService.searchById(id);

		return response;
			
	}
	@PostMapping("/products/filter/{name}")
	public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name){
		
		ResponseEntity<ProductResponseRest> response = productService.searchByName(name);

		return response;
			
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> borrar(@PathVariable Long id){
		
		ResponseEntity<ProductResponseRest> response = productService.deleteById(id);

		return response;
			
	}
	
	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> search(){
		
		ResponseEntity<ProductResponseRest> response = productService.search();

		return response;
			
	}
	
	/**
	 * export product in excel file
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/products/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=result_product.xlsx";
		response.setHeader(headerKey, headerValue);
		
		ResponseEntity<ProductResponseRest> products = productService.search();
		
		ProductExcelExporter excelExporter = new ProductExcelExporter(
				products.getBody().getProduct().getProducts());
		
		excelExporter.export(response);
				
		
	}

}
