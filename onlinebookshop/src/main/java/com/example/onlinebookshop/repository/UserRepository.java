package com.example.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinebookshop.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
	
    Optional<User> findByUsername(String username);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
}
