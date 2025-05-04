package com.example.inventory.response;

import java.util.List;

import com.example.inventory.model.Product;

import lombok.Data;

@Data
public class ProductResponse {
	private List<Product> products;

}
