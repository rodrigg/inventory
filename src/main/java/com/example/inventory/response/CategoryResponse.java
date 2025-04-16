package com.example.inventory.response;

import java.util.List;

import com.example.inventory.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {
	private List<Category> category;

}
