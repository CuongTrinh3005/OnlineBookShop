package com.example.onlinebookshop.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.onlinebookshop.model.User;
import com.example.onlinebookshop.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Test
	public void getAllUsers() {
		List<User> listUsers = new ArrayList<User>();

		User user1 = new User("Cuong", "123", "Trinh Quoc Cuong", true, "TP HCM", "1234556", "cuong123", null, null,
				null, null);
		User user2 = new User("Nam", "123", "Nguyen Van A", false, "TP HCM", "1234556", "a123", null, null, null, null);

		listUsers.add(user1);
		listUsers.add(user2);

		when(userService.getAllUsers()).thenReturn(listUsers);
		// When
		List<User> users = userService.getAllUsers();
		// Then
		assertEquals(2, users.size());
		verify(userRepository, times(1)).findAll();
	}

	@Test
	public void getUserByUserName() {
		User userTest = new User("Cuong", "123", "Trinh Quoc Cuong", true, "TP HCM", "1234556", "cuongtq@gmail.com",
				null, null, null, null);
		Optional<User> userOpt = Optional.of(userTest);
		when(userRepository.findByUsername("Cuong")).thenReturn(userOpt);

		// When
		User user = userService.findByUserName("Cuong").get();
		// Then
		assertEquals("123", user.getPassword());
		assertEquals("Trinh Quoc Cuong", user.getFullName());
		assertEquals("1234556", user.getPhoneNumber());
		assertEquals("cuongtq@gmail.com", user.getEmail());
	}
}