package com.example.onlinebookshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Author;
import com.example.onlinebookshop.repository.AuthorRepository;
import com.example.onlinebookshop.service.AuthorService;

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
	public Author updateAuthor(Author author, Integer id) {
		Optional<Author> authorOpt = findAuthorById(id);

		Author existedAuthor = authorOpt.get();

		existedAuthor.setAuthorName(author.getAuthorName());
		existedAuthor.setAddress(author.getAddress());;
		existedAuthor.setPhoneNumber(author.getPhoneNumber());
		
		return saveAuthor(existedAuthor);
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