package com.example.onlinebookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Author;
import com.example.onlinebookshop.repository.AuthorRepository;
import com.example.onlinebookshop.service.impl.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	AuthorRepository authorRepository;

	@Override
	public Optional<Author> findAuthorById(Integer id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author id " + id + " not found"));
		return Optional.of(author);
	}

	@Override
	public List<Author> findAllById(List<Integer> listAuthorId) {
		return authorRepository.findAllById(listAuthorId);
	}

	@Override
	public Author findByAuthorName(String name) {
		return authorRepository.findByAuthorName(name);
	}

	@Override
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public void deleteAuthor(Integer id) {
		authorRepository.deleteById(id);
	}
}