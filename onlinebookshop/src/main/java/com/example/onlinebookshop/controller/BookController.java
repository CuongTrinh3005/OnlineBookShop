package com.example.onlinebookshop.controller;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.onlinebookshop.exception.CustomException;
import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.dto.BookDTO;
import com.example.onlinebookshop.service.BookService;

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
	public ResponseEntity<Book> saveBook(@Valid @RequestBody BookDTO bookDTO){
		Set<ConstraintViolation<BookDTO>> result = validator.validate(bookDTO);
		if (!result.isEmpty()) {
			// do here whatever you want with each validation violation.
			System.out.println("BookDTO is invalid!");
			throw new CustomException("Check your input");
		}
		Book book = bookService.saveBook(bookDTO);
		
		//Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(book.getBookId())
                                    .toUri();
        
        return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	@PutMapping("books/{id}")
	public ResponseEntity<Book> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id) {
		Set<ConstraintViolation<BookDTO>> result = validator.validate(bookDTO);
		if (!result.isEmpty()) {
			// do here whatever you want with each validation violation.
			System.out.println("BookDTO is invalid!");
			throw new CustomException("Check your input");
		}
		
		return new ResponseEntity<Book>(bookService.updateBook(bookDTO, id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("books/{id}")
	public void deleteBook(@PathVariable Long id){
		Optional<Book> bookOtp = bookService.getBookById(id);
		Book book = bookOtp.get();
		
		if(book.getOrderDetails().size()==0 && book.getRatings().size()==0)
			bookService.deleteBook(book);
		else
			throw new CustomException("Can not delete books having in order đetails or ratings!");
	}
}