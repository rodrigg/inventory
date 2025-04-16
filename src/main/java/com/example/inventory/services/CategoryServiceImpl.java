package com.example.inventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventory.dao.ICategaryDao;
import com.example.inventory.model.Category;
import com.example.inventory.response.CategoryResponseRest;


@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	private ICategaryDao categoryDao;
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CategoryResponseRest> search() {
		CategoryResponseRest response = new CategoryResponseRest();
		
		try {
			List<Category> listaCategories =  (List<Category>) categoryDao.findAll();
			response.getCategoryResponse().setCategory(listaCategories);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
