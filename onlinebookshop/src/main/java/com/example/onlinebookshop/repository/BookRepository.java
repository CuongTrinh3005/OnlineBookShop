package com.example.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinebookshop.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
}
