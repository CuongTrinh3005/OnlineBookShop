package com.example.onlinebookshop.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.onlinebookshop.entity.User;

public interface UserService {
	List<User> getAllUsers();

	Optional<User> findByUserName(String userName);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	void delete(User user);
	
	User saveUser(User user);
}
