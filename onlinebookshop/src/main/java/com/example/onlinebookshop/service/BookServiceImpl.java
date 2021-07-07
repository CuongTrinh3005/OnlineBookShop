package com.example.onlinebookshop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.OnlinebookshopApplication;
import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Author;
import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.Category;
import com.example.onlinebookshop.model.Publisher;
import com.example.onlinebookshop.model.dto.BookDTO;
import com.example.onlinebookshop.repository.BookRepository;
import com.example.onlinebookshop.repository.CategoryRepository;
import com.example.onlinebookshop.service.impl.AuthorService;
import com.example.onlinebookshop.service.impl.BookService;
import com.example.onlinebookshop.service.impl.PublisherService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;

	// @Autowired
	// ModelMapper mapper;

	@Autowired
	CategoryRepository categoryService;

	@Autowired
	PublisherService publisherService;

	@Autowired
	AuthorService authorService;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> getBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book id " + id + " not found"));
		return Optional.of(book);
	}

	@Override
	public List<Book> getBookByName(String name) {
		List<Book> book = bookRepository.findByBookName(name);
		if (book == null)
			throw new ResourceNotFoundException("Book name " + name + " not found");
		return book;
	}

	@Override
	public List<Book> getBookByAuthorName(String authorName) {
		List<Book> book = bookRepository.findBookByAuthors_AuthorName(authorName);
		if (book == null)
			throw new ResourceNotFoundException("Book with author " + authorName + " not found");
		return book;
	}

	@Override
	public List<Book> getBookByPublisherName(String publisherName) {
		List<Book> book = bookRepository.findBookByPublisher_PublisherName(publisherName);
		if (book == null)
			throw new ResourceNotFoundException("Book with publisher " + publisherName + " not found");
		return book;
	}

	@Override
	public List<Book> getBookByCategoryName(String categoryName) {
		List<Book> book = bookRepository.findBookByCategory_CategoryName(categoryName);
		if (book == null)
			throw new ResourceNotFoundException("Book with publisher " + categoryName + " not found");
		return book;
	}

	@Override
	public BookDTO convertBookToDTO(Book book) {
		if (book == null)
			throw new ResourceNotFoundException("Book is not found!");

		BookDTO bookDTO = new BookDTO();
		bookDTO.setBookId(book.getBookId());
		bookDTO.setBookName(book.getBookName());
		bookDTO.setCategoryName(book.getCategory().getCategoryName());
		bookDTO.setDescription(book.getDescription());
		bookDTO.setDiscount(book.getDiscount());
		bookDTO.setAvailable(book.getAvailable());
		bookDTO.setPhoto(book.getPhoto());
		bookDTO.setPublisherName(book.getPublisher().getPublisherName());
		bookDTO.setQuantity(book.getQuantity());
		bookDTO.setUnitPrice(book.getUnitPrice());
		bookDTO.setSpecial(book.getSpecial());
		bookDTO.setSpecification(book.getSpecification());
		bookDTO.setViewCount(book.getViewCount());

		List<Author> authors = new ArrayList<>(book.getAuthors());
		if (authors.size() > 0) {
			String[] authorIds = new String[authors.size()];
			for (int index = 0; index < authorIds.length; index++) {
				authorIds[index] = String.valueOf(authors.get(index).getAuthorId());
			}
			bookDTO.setAuthorIds(authorIds);
		}

		return bookDTO;
	}

	@Override
	public Book convertBookDtoToBook(BookDTO bookDTO) {
		if (bookDTO == null)
			throw new ResourceNotFoundException("BookDTO is not found!");
		Book book = new Book();
		book.setBookName(bookDTO.getBookName());
		book.setDescription(bookDTO.getDescription());
		book.setDiscount(bookDTO.getDiscount());
		book.setAvailable(bookDTO.getAvailable());
		book.setPhoto(bookDTO.getPhoto());

		book.setQuantity(bookDTO.getQuantity());
		book.setUnitPrice(bookDTO.getUnitPrice());
		book.setSpecial(bookDTO.getSpecial());
		book.setSpecification(bookDTO.getSpecification());
		book.setViewCount(bookDTO.getViewCount());

		Category category = categoryService.findByCategoryName(bookDTO.getCategoryName());
		book.setCategory(category);

		Publisher publisher = publisherService.findPublisherByName(bookDTO.getPublisherName());
		book.setPublisher(publisher);

		String[] authorIds = bookDTO.getAuthorIds();
		List<Integer> listAuthorIds = OnlinebookshopApplication.convertStringArrToIntArr(authorIds);
		List<Author> listAuthor = authorService.findAllById(listAuthorIds);
		book.setAuthors(listAuthor.stream().collect(Collectors.toSet()));
		
		return book;
	}

	@Override
	public Book saveBook(BookDTO bookDTO) {
		Book book = convertBookDtoToBook(bookDTO);
		book.setDateIn(new Date());
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(BookDTO bookDTO, Long id) {
		Book book = getBookById(id).get();
		BookDTO existedBookDTO = convertBookToDTO(book);
		
		existedBookDTO.setBookName(bookDTO.getBookName());
		existedBookDTO.setCategoryName(bookDTO.getCategoryName());
		existedBookDTO.setDescription(bookDTO.getDescription());
		existedBookDTO.setDiscount(bookDTO.getDiscount());
		existedBookDTO.setAvailable(bookDTO.getAvailable());
		existedBookDTO.setPhoto(bookDTO.getPhoto());
		existedBookDTO.setPublisherName(bookDTO.getPublisherName());
		existedBookDTO.setQuantity(bookDTO.getQuantity());
		existedBookDTO.setUnitPrice(bookDTO.getUnitPrice());
		existedBookDTO.setSpecial(bookDTO.getSpecial());
		existedBookDTO.setSpecification(bookDTO.getSpecification());
		existedBookDTO.setViewCount(bookDTO.getViewCount());
		existedBookDTO.setAuthorIds(bookDTO.getAuthorIds());
		
		Book updatingBook = convertBookDtoToBook(existedBookDTO);
		updatingBook.setBookId(id);
		updatingBook.setDateUpdate(new Date());
		
		return bookRepository.save(updatingBook);
	}

	@Override
	public void deleteBook(Book book) {
		bookRepository.delete(book);
	}
	
}