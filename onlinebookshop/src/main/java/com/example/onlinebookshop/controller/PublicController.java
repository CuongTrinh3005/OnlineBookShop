package com.example.onlinebookshop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinebookshop.model.Author;
import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.Category;
import com.example.onlinebookshop.model.Publisher;
import com.example.onlinebookshop.model.Rating;
import com.example.onlinebookshop.model.dto.BookDTO;
import com.example.onlinebookshop.payload.request.ChangePasswordRequest;
import com.example.onlinebookshop.service.AuthorService;
import com.example.onlinebookshop.service.BookService;
import com.example.onlinebookshop.service.CategoryService;
import com.example.onlinebookshop.service.EmailService;
import com.example.onlinebookshop.service.PublisherService;
import com.example.onlinebookshop.service.RatingService;
import com.example.onlinebookshop.service.UserService;

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
	
	@Autowired
	RatingService ratingService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	UserService userService;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
	
	@RequestMapping("/send-email")
	public Boolean sendEmail(){
		try{
			String to = "trinhquoccuong3005@gmail.com";
			String subject = "Test sending email";
			String text = "Test sending with config in application.properties!";
			emailService.sendSimpleMessage(to, subject, text);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	@GetMapping("reset-password")
	public Boolean resetPassword(@RequestParam String username){
		return userService.resetPassword(username);
	}
	
	@GetMapping("categories")
	public List<Category> getCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping("categories/{id}")
	public Optional<Category> retrieveCategory(@PathVariable String id) {
		return categoryService.findById(id);
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
	
	@GetMapping("books/ascending")
	public List<BookDTO> getAllBooksAscendingOrder() {
		return bookService.getListBookByAscendingOrder().stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("books/{id}")
	public Optional<BookDTO> retrieveBook(@PathVariable Long id) {
		Book book = bookService.getBookById(id).get();
		book.setViewCount(book.getViewCount()+1);

		BookDTO dto = bookService.convertBookToDTO(book);
		bookService.updateBook(dto, dto.getBookId());

		return Optional.of(bookService.convertBookToDTO(book));
	}

	@GetMapping("books/new")
	public List<BookDTO> getNewBooks() {
		return bookService.getListBookByDateInDesc().stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());		
	}
	
	@GetMapping("books/discounting")
	public List<BookDTO> getDiscountingBooks() {
		return bookService.getListBookByDiscountDesc().stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());		
	}

	@GetMapping("books/top-view")
	public List<BookDTO> getTopViewBooks() {
		return bookService.getListBookByViewCountDesc().stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());
	}

	@GetMapping("books/best-selling")
	public List<BookDTO> getTopBestSellingBooks() {
		return bookService.getListBestSellingBook(0,10).stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());
	}

	@GetMapping("ratings/books/{id}")
	public List<Rating> getBookRatings(@PathVariable Long id){
		return ratingService.getAllRatingByBookId(id);
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

	@GetMapping("books/search/")
	public List<BookDTO> getBooksByInfo(@RequestParam String name) {
		return bookService.getListBookContainsName(name).stream().map(bookService::convertBookToDTO)
				.collect(Collectors.toList());
	}

	@GetMapping("books/category/{id}")
	public List<BookDTO> getBooksByCategoryId(@PathVariable String id) {
		return bookService.getBookByCategoryId(id).stream().map(bookService::convertBookToDTO)
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
	
	@GetMapping("authors/{id}")
	public Optional<Author> getAuthorById(@PathVariable Integer id){
		Optional<Author> author = authorService.findAuthorById(id);
		return author;
	}
}