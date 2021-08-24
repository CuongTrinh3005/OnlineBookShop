package com.example.onlinebookshop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.onlinebookshop.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByBookName(String bookName);
	List<Book> findBookByAuthors_AuthorName(String authorName);
	List<Book> findBookByPublisher_PublisherName(String publisherName);
	List<Book> findBookByCategory_CategoryName(String categoryName);
	List<Book> findTop10ByOrderByDateInDesc();
	List<Book> findTop10ByOrderByDiscountDesc();
	List<Book> findTop10ByOrderByViewCountDesc();
	List<Book> findAllByOrderByBookIdAsc();
	@Query("FROM Book b ORDER BY size(b.orderDetails) DESC")
	List<Book> findTopBestSellingBook(Pageable pageable);
}