package com.example.onlinebookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.onlinebookshop.entity.User;
import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.repository.UserRepository;
import com.example.onlinebookshop.service.impl.UserService;

@Service
public class UserServiceImpl implements UserService {

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByUserName(String userName) throws UsernameNotFoundException{
		User user = userRepository.findByUsername(userName)
				.orElseThrow(()-> new ResourceNotFoundException(userName + " not found"));
		return Optional.of(user);				
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
}