package com.example.onlinebookshop.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.onlinebookshop.model.User;

import com.example.onlinebookshop.service.impl.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private UserController userController;

	@Mock
	UserService userService;

	private List<User> userList= new ArrayList<>();
	@BeforeEach
	void setUp() {
		User user1 = new User("Cuong", "123", "Trinh Quoc Cuong", true, "TP HCM", "1234556", "cuong123", null, null,
				null, null);
		User user2 = new User("Nam", "123", "Nguyen Van A", false, "TP HCM", "1234556", "a123", null, null, null, null);
		this.userList.add(user1);
		this.userList.add(user2);
	}

	@Test
	public void getAllTest() throws Exception {
		when(userService.getAllUsers())
		.thenReturn(this.userList);
		this.mockMvc.perform(get("/api/v1/admin/users/"))
					.andExpect(status().isUnauthorized());
	}
}