package com.example.onlinebookshop.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.onlinebookshop.model.User;
import com.example.onlinebookshop.repository.UserRepository;
import com.example.onlinebookshop.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserServiceImpl userService;
	@MockBean
	private UserRepository userRepo;

	private List<User> userList;

	@BeforeEach
	void setUp() {
		this.userList = new ArrayList<>();
		User user1 = new User("Cuong", "123", "Trinh Quoc Cuong", true, "TP HCM", "1234556", "cuong123", null, null,
				null, null);
		User user2 = new User("Nam", "123", "Nguyen Van A", false, "TP HCM", "1234556", "a123", null, null, null, null);
		this.userList.add(user1);
		this.userList.add(user2);
	}

	@Test
	@WithMockUser(username = "admin")
	public void getAllEmployeesAPI() throws Exception {

		List<User> userList = new ArrayList<>();
		User user1 = new User("Cuong", "123", "Trinh Quoc Cuong", true, "TP HCM", "1234556", "cuong123", null, null,
				null, null);
		User user2 = new User("Nam", "123", "Nguyen Van A", false, "TP HCM", "1234556", "a123", null, null, null, null);
		userList.add(user1);
		userList.add(user2);
		
		when(userRepo.findAll()).thenReturn(userList);
		this.mvc.perform(get("/api/v1/admin/users/")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(userList.size())));

	}
}