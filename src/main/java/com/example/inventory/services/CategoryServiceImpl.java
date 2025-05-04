package com.example.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventory.dao.ICategoryDao;
import com.example.inventory.model.Category;
import com.example.inventory.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {
		CategoryResponseRest response = new CategoryResponseRest();

		try {
			List<Category> listaCategories = (List<Category>) categoryDao.findAll();
			response.getCategoryResponse().setCategory(listaCategories);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchByUd(Long id) {

		CategoryResponseRest response = new CategoryResponseRest();

		try {
			Optional<Category> categoryById = categoryDao.findById(id);
			List<Category> listaCategories = new ArrayList<>();
			if (categoryById.isPresent()) {
				listaCategories.add(categoryById.get());
				response.getCategoryResponse().setCategory(listaCategories);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

			} else {
				response.getCategoryResponse().setCategory(listaCategories);
				response.setMetadata("Respuesta nook", "-1", "Respuesta NO ENCONTRADA");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<CategoryResponseRest> save(Category category) {

		CategoryResponseRest response = new CategoryResponseRest();

		try {
			Category categorySaved = categoryDao.save(category);
			List<Category> listaCategories = new ArrayList<>();
			if (categorySaved != null) {
				listaCategories.add(categorySaved);
				response.getCategoryResponse().setCategory(listaCategories);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

			} else {
				response.getCategoryResponse().setCategory(listaCategories);
				response.setMetadata("Respuesta nook", "-1", "Respuesta NO GUARDADA");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al GUARDAR");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<CategoryResponseRest> actualizar(Category category, Long id) {

		CategoryResponseRest response = new CategoryResponseRest();

		try {
			Optional<Category> categoryById = categoryDao.findById(id);
			List<Category> listaCategories = new ArrayList<>();
			if (categoryById.isPresent()) {
				Category categoryMod = categoryById.get();
				categoryMod.setId(id);
				categoryMod.setDescription(category.getDescription());
				categoryMod.setName(category.getName());
				Category categorySave = categoryDao.save(categoryMod);
				listaCategories.add(categorySave);
				response.getCategoryResponse().setCategory(listaCategories);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

			} else {
				response.getCategoryResponse().setCategory(listaCategories);
				response.setMetadata("Respuesta nook", "-1", "Respuesta NO ENCONTRADA");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al GUARDAR");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
		CategoryResponseRest response = new CategoryResponseRest();

		try {
			categoryDao.deleteById(id);
			response.getCategoryResponse().setCategory(null);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al borrar");
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}
}
