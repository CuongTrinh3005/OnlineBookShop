package com.example.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlinebookshop.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
	Category findByCategoryName(String categoryName);
	Boolean existsByCategoryId(String categoryId);
}
