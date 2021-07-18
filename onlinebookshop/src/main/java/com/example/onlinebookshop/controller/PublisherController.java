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
import com.example.onlinebookshop.model.Publisher;

import com.example.onlinebookshop.service.impl.PublisherService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class PublisherController {

	@Autowired
	PublisherService publisherService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("publishers")
	public List<Publisher> getAllPublishers() {
		return publisherService.getAllPublishers();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("publishers/{id}")
	public Optional<Publisher> getPublisherById(@PathVariable Integer id) {
		Optional<Publisher> publisher = publisherService.findPublisherById(id);
		return publisher;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("publishers")
	public ResponseEntity<Author> savePublisher(@Valid @RequestBody Publisher publisher) {
		publisherService.savePublisher(publisher);

		// Create resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(publisher.getPublisherId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("publishers/{id}")
	public ResponseEntity<Publisher> updatePublisher(@Valid @RequestBody Publisher publisher,
			@PathVariable Integer id) {
		return new ResponseEntity<Publisher>(publisherService.updatePublisher(publisher, id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("publishers/{id}")
	public void deletePublisher(@PathVariable Integer id) {
		Optional<Publisher> publisherOpt = publisherService.findPublisherById(id);

		Publisher existedPublisher = publisherOpt.get();

		if (existedPublisher.getBooks().size() > 0)
			throw new CustomException("Can not delete publisher having books");

		publisherService.deletePublisher(id);
	}
}