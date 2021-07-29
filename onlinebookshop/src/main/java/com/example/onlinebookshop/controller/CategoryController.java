package com.example.onlinebookshop.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.onlinebookshop.exception.CustomException;
import com.example.onlinebookshop.exception.ResourceAlreadyExistedException;
import com.example.onlinebookshop.model.Category;
import com.example.onlinebookshop.service.impl.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("categories/{id}")
	public Optional<Category> retrieveCategory(@PathVariable String id) {
		return categoryService.findById(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("categories")
	public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category) {
		Boolean existed = categoryService.existsById(category.getCategoryId());
		if(existed) throw new ResourceAlreadyExistedException("Resource already existed");
		
		Boolean existedName = categoryService.existByName(category.getCategoryName());
		if(existedName) throw new ResourceAlreadyExistedException("Category name already existed");
		
		categoryService.saveCategory(category);
		
		//Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(category.getCategoryId())
                                    .toUri();
        
        return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("categories/{id}")
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category, @PathVariable String id) {
		return new ResponseEntity<Category>(categoryService.updateCategory(category, id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("categories/{id}")
	public void deleteCategory(@PathVariable String id) {
		Optional<Category> categoryOpt = categoryService.findById(id);

		Category category = categoryOpt.get();
		if (category.getBooks().size() == 0)
			categoryService.delete(category);
		else
			throw new CustomException("Not allow to delete category having books");
	}
}