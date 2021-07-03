package com.example.onlinebookshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinebookshop.entity.User;
import com.example.onlinebookshop.exception.ResourceNotFoundException;
import com.example.onlinebookshop.service.impl.UserService;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Autowired
	UserService userService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("users/{username}")
	public void deleteUser(@PathVariable String username) throws ResourceNotFoundException{
		Optional<User> userOpt = userService.findByUserName(username);
		if(userOpt.isPresent()){
			User user = userOpt.get();
			if(user.getOrders().size()==0 && user.getRatings().size()==0)
				userService.delete(user);
			else
				throw new RuntimeException("Not allow to delete user having orders or ratings");
		}	
	}
}
