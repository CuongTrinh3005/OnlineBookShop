package com.example.onlinebookshop.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.dto.BookDTO;

public interface BookService{
	List<Book> getAllBooks();
	Optional<Book> getBookById(Long id);
	List<Book> getBookByName(String name);
	List<Book> getBookByAuthorName(String authorName);
	List<Book> getBookByPublisherName(String publisherName);
	List<Book> getBookByCategoryName(String categoryName);
	BookDTO convertBookToDTO(Book book);
	Book convertBookDtoToBook(BookDTO bookDTO);
	Book saveBook(BookDTO bookDTO);
	Book updateBook(BookDTO bookDTO, Long id);
	void deleteBook(Book book);
	List<Book> getListBookByDateInDesc();
	List<Book> getListBookByDiscountDesc();
	List<Book> getListBookByAscendingOrder();
//	Boolean updateBookQuantity(Long id, int quantity);
}