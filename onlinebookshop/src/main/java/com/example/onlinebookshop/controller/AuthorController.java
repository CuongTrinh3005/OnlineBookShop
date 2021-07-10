package com.example.onlinebookshop.controller;

import java.net.URI;
import java.util.List;
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
import com.example.onlinebookshop.model.Author;
import com.example.onlinebookshop.model.Book;

import com.example.onlinebookshop.service.impl.AuthorService;
import com.example.onlinebookshop.service.impl.BookService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	@Autowired
	BookService bookService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("authors")
	public List<Author> getAllAuthors(){
		return authorService.getAllAuthors();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("authors/{id}")
	public Optional<Author> getAuthorById(@PathVariable Integer id){
		Optional<Author> author = authorService.findAuthorById(id);
		return author;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("authors")
	public ResponseEntity<Author> saveAuthor(@Valid @RequestBody Author author){
		authorService.saveAuthor(author);
		
		//Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(author.getAuthorId())
                                    .toUri();
        
        return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("authors/{id}")
	public ResponseEntity<Author> updateAuthor(@Valid @RequestBody Author author, @PathVariable Integer id) {
		Optional<Author> authorOpt = authorService.findAuthorById(id);

		Author existedAuthor = authorOpt.get();

		existedAuthor.setAuthorName(author.getAuthorName());
		existedAuthor.setAddress(author.getAddress());;
		existedAuthor.setPhoneNumber(author.getPhoneNumber());

		return new ResponseEntity<Author>(authorService.saveAuthor(existedAuthor), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("authors/{id}")
	public void deleteCategory(@PathVariable Integer id) {
		Optional<Author> authorOpt = authorService.findAuthorById(id);
		Author existedAuthor = authorOpt.get();
		
		List<Book> bookWritenByAuthor = bookService.getBookByAuthorName(existedAuthor.getAuthorName());
		if(bookWritenByAuthor.size()>0)
			throw new CustomException("Can not delete author having books");
		
		authorService.deleteAuthor(id);
	}
}
