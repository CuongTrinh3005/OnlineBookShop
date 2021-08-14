package com.example.onlinebookshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Category;
import com.example.onlinebookshop.repository.CategoryRepository;
import com.example.onlinebookshop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findCategoryByName(String name) {
		Category category = categoryRepository.findByCategoryName(name);
		if (category == null)
			throw new ResourceNotFoundException("category name " + name + " not found");

		return Optional.of(category);
	}

	@Override
	public Optional<Category> findById(String id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category id " + id + " not found"));
		return Optional.of(category);
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category, String id) {
		Optional<Category> categoryOpt = findById(id);

		Category existedCategory = categoryOpt.get();

		existedCategory.setCategoryId(category.getCategoryId());
		existedCategory.setCategoryName(category.getCategoryName());
		existedCategory.setDescription(category.getDescription());
		existedCategory.setBooks(category.getBooks());
		
		return saveCategory(existedCategory);
	}

	@Override
	public Boolean existsById(String id) {
		return categoryRepository.existsByCategoryId(id);
	}

	@Override
	public Boolean existByName(String name) {
		return categoryRepository.existsByCategoryName(name);
	}
}