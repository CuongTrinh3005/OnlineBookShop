package com.example.onlinebookshop.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.Author;

public interface AuthorService {
	Optional<Author> findAuthorById(Integer id);
	List<Author> findAllById(List<Integer> listAuthorId);
	List<Author> getAllAuthors();
	Author findByAuthorName(String name);
	Author saveAuthor(Author author);
	void deleteAuthor(Integer id);
	Author updateAuthor(Author author, Integer id);
}
