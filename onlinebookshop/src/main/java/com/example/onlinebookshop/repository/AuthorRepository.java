package com.example.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlinebookshop.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
