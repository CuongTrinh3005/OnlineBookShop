package com.example.onlinebookshop.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.onlinebookshop.exception.CustomException;
import com.example.onlinebookshop.exception.ResourceAlreadyExistedException;
import com.example.onlinebookshop.model.Book;
import com.example.onlinebookshop.model.Role;
import com.example.onlinebookshop.model.User;
import com.example.onlinebookshop.model.dto.BookDTO;
import com.example.onlinebookshop.model.dto.UserDTO;
import com.example.onlinebookshop.service.impl.RoleService;
import com.example.onlinebookshop.service.impl.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	Validator validator;

	@GetMapping("roles")
	public List<Role> findRoleByListIds(){
		Integer a = 1, b=2;
		
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(a);
		ids.add(b);
		return roleService.findAllById(ids);
	}
	
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
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody UserDTO userDTO){
		Set<ConstraintViolation<UserDTO>> result = validator.validate(userDTO);
		if (!result.isEmpty()) {
			// do here whatever you want with each validation violation.
			System.out.println("BookDTO is invalid!");
			throw new CustomException("Check your input");
		}
		Boolean existed = userService.existsByUsername(userDTO.getUsername());
		if(existed) throw new ResourceAlreadyExistedException("Username has already existed!");
		
		User user = userService.createNewUser(userDTO);
		//Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{username}")
                                    .buildAndExpand(user.getUserName())
                                    .toUri();
        
        return ResponseEntity.created(location).build();
	}
}