package com.example.onlinebookshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.onlinebookshop.model.User;
import com.example.onlinebookshop.model.dto.UserDTO;
import com.example.onlinebookshop.model.dto.UserDTOString;

@Service
public interface UserService {
	List<User> getAllUsers();

	Optional<User> findByUserName(String userName);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	void delete(User user);
	
	User saveUser(User user);
	
	User convertUserDtoToUser(UserDTO userDTO);
	UserDTOString convertUserToUserDTOString(User user);
	User createNewUser(UserDTO userDTO);
	User updateUser(UserDTO userDTO, String username);
}
