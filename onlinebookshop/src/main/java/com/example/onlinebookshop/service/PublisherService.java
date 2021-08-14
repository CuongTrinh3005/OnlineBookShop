package com.example.onlinebookshop.service;

import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.model.Publisher;

public interface PublisherService {
	Publisher findPublisherByName(String publisherName);
	Optional<Publisher> findPublisherById(Integer id);
	List<Publisher> getAllPublishers();
	Publisher savePublisher(Publisher publisher);
	void deletePublisher(Integer id);
	Publisher updatePublisher(Publisher publisher, Integer id);
}