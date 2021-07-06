package com.example.onlinebookshop.service.impl;

import com.example.onlinebookshop.model.Publisher;

public interface PublisherService {
	Publisher findPublisherByName(String publisherName);
}