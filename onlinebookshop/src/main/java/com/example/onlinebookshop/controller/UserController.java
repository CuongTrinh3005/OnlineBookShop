package com.example.onlinebookshop.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinebookshop.entity.User;
import com.example.onlinebookshop.service.impl.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("users/{username}")
	public Optional<User> getUserByUserName(@PathVariable String username) {
		System.out.println("Username in URI: " + username);
		return userService.findByUserName(username);
	}

	@PutMapping("users/{username}")
	public ResponseEntity<User> updateDetails(@Valid @RequestBody User user, @PathVariable String username) {
		Optional<User> userOpt = userService.findByUserName(username);

		User existedUser = userOpt.get();
		
		existedUser.setUserName(user.getUserName());
		existedUser.setFullName(user.getFullName());
		existedUser.setGender(user.getGender());
		existedUser.setPhoneNumber(user.getPhoneNumber());
		existedUser.setAddress(user.getAddress());
		existedUser.setPhoto(user.getPhoto());
		existedUser.setRoles(user.getRoles());

		return new ResponseEntity<User>(userService.saveUser(existedUser), HttpStatus.OK);
	}
	
	@PutMapping("users/password/{username}")
	public ResponseEntity<User> changePassword(@Valid @RequestBody User user, @PathVariable String username) {
		Optional<User> userOpt = userService.findByUserName(username);

		User existedUser = userOpt.get();
		
		System.out.println("Username: " + username);
		System.out.println("Password: "+ user.getPassword());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
		existedUser.setPassword(encodedPassword);

		return new ResponseEntity<User>(userService.saveUser(existedUser), HttpStatus.OK);
	}
}
