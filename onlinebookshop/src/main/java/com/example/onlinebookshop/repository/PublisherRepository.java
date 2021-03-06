package com.example.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlinebookshop.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
	Publisher findByPublisherName(String publisherName);
	Boolean existsByPublisherName(String publisherName);
}
