package com.example.onlinebookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.Publisher;
import com.example.onlinebookshop.repository.PublisherRepository;
import com.example.onlinebookshop.service.impl.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{
	@Autowired
	PublisherRepository publisherRepository;

	@Override
	public Publisher findPublisherByName(String publisherName) {
		Publisher publisher = publisherRepository.findByPublisherName(publisherName);
		if(publisher == null) throw new ResourceNotFoundException("Can not find publisher");
		return publisher;
	}
}
