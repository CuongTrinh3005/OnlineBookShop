package com.example.onlinebookshop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinebookshop.exception.CustomException;
import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.model.User;
import com.example.onlinebookshop.model.dto.UserDTORoleString;
import com.example.onlinebookshop.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("users")
	public List<UserDTORoleString> getAllUsers() {
		return userService.getAllUsers().stream().map(userService::convertUserToUserDTOString)
				.collect(Collectors.toList());
	} 

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("users/{username}")
	public void deleteUser(@PathVariable String username) throws ResourceNotFoundException {
		Optional<User> userOpt = userService.findByUserName(username);

		User user = userOpt.get();
		if (user.getOrders().size() == 0 && user.getRatings().size() == 0)
			userService.delete(user);
		else
			throw new CustomException("Not allow to delete user having orders or ratings");
	}
}