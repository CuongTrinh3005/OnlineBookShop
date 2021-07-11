package com.example.onlinebookshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.CoreMatchers.containsString;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.onlinebookshop.controller.CategoryController;
import com.example.onlinebookshop.model.Category;

import com.example.onlinebookshop.service.impl.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
	@Autowired
	private MockMvc mvc;

	@InjectMocks
	CategoryController controller;

	@Mock
	CategoryService categoryService;

	private List<Category> categoryList = new ArrayList<Category>();

	@BeforeEach
	void setUp() {
		Category c1 = new Category("a", "a", "a", null);
		Category c2 = new Category("b", "b", "b", null);
		this.categoryList.add(c1);
		this.categoryList.add(c2);
	}

	@Test
	public void getAllCategories() throws Exception{
		when(categoryService.getAllCategories())
		.thenReturn(categoryList);
		this.mvc.perform(get("/api/public/categories/"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("categoryId")))
		.andExpect(MockMvcResultMatchers.jsonPath("$[*].categoryId").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$[*].categoryName").isNotEmpty());
	}
}