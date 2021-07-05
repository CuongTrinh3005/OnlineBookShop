package com.example.onlinebookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.entity.Category;
import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.repository.CategoryRepository;
import com.example.onlinebookshop.service.impl.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategries() {
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
	public Boolean existsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}
}