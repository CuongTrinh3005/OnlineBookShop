package com.example.onlinebookshop.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinebookshop.exception.CustomException;
import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.dto.BookDTO;
import com.example.onlinebookshop.service.impl.BookService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class BookController {
	@Autowired
	BookService bookService;

	@Autowired
	Validator validator;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("books")
	public Book saveBook(@Valid @RequestBody BookDTO bookDTO){
		Set<ConstraintViolation<BookDTO>> result = validator.validate(bookDTO);
		if (!result.isEmpty()) {
			// do here whatever you want with each validation violation.
			System.out.println("BookDTO is invalid!");
			throw new CustomException("Check your input");
		}
		return bookService.saveBook(bookDTO);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("books/{id}")
	public Book updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id) {
		Set<ConstraintViolation<BookDTO>> result = validator.validate(bookDTO);
		if (!result.isEmpty()) {
			// do here whatever you want with each validation violation.
			System.out.println("BookDTO is invalid!");
			throw new CustomException("Check your input");
		}
		
		return bookService.convertBookDtoToBook(bookDTO);
	}
}