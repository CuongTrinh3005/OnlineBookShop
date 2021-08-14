package com.example.onlinebookshop.service;

import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.Category;

public interface CategoryService {
	List<Category> getAllCategories();

	Optional<Category> findCategoryByName(String name);
	
	Optional<Category> findById(String id);

	Boolean existsById(String id);

	void delete(Category category);
	
	Category saveCategory(Category category);
	
	Category updateCategory(Category category, String id);
	
	Boolean existByName(String name);
}
