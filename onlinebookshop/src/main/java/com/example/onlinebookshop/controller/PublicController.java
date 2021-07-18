package com.example.onlinebookshop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinebookshop.model.Author;
import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.Category;
import com.example.onlinebookshop.model.Publisher;
import com.example.onlinebookshop.model.dto.BookDTO;
import com.example.onlinebookshop.service.impl.AuthorService;
import com.example.onlinebookshop.service.impl.BookService;
import com.example.onlinebookshop.service.impl.CategoryService;
import com.example.onlinebookshop.service.impl.PublisherService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class PublicController {
	@Autowired
	CategoryService categoryService;

	@Autowired
	BookService bookService;
	
	@Autowired
	PublisherService publisherService;
	
	@Autowired
	AuthorService authorService;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
	
	@GetMapping("categories")
	public List<Category> getCategories() {
		return categoryService.getAllCategories();
	}
	
	@GetMapping("categories/search")
	public Optional<Category> getCategoryByName(@RequestParam String name) {
		return categoryService.findCategoryByName(name);
	}

	@GetMapping("books")
	public List<BookDTO> getAllBooks() {
		return bookService.getAllBooks().stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("books/{id}")
	public Optional<BookDTO> retrieveBook(@PathVariable Long id) {
		Book book = bookService.getBookById(id).get();
		return Optional.of(bookService.convertBookToDTO(book));
	}

	@GetMapping("books/search") // update url to /users/search/items?
	public List<BookDTO> retriveBookByName(@RequestParam String name) {
		return bookService.getBookByName(name).stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());
	}

	@GetMapping("books/search/category")
	public List<BookDTO> getBooksByCategoryName(@RequestParam String name) {
		return bookService.getBookByCategoryName(name).stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());
	}

	@GetMapping("books/search/author")
	public List<BookDTO> getBooksByAuthorName(@RequestParam String name) {
		return bookService.getBookByAuthorName(name).stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());
	}

	@GetMapping("books/search/publisher")
	public List<BookDTO> getBooksByPublisherName(@RequestParam String name) {
		return bookService.getBookByPublisherName(name).stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());
	}

	@GetMapping("publishers")
	public Publisher getPublisherByPublisherName(@RequestParam String name) {
		return publisherService.findPublisherByName(name);
	}
	
	@GetMapping("authors")
	public Author getAuthorByName(@RequestParam String name){
		return authorService.findByAuthorName(name);
	}
}