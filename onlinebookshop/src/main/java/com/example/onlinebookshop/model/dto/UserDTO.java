package com.example.onlinebookshop.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
public class UserDTO {
	private String username;
	@Size(min=4, max = 100)
	private String password;
	@NotBlank
    @Size(min=3, max = 50)
	private String fullName;
	@NotBlank
	@Size(max = 50)
    @Email
	private String email;
	@Size(min = 1)
	private String[] roleIds = new String[0];
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(String username, @Size(min = 4, max = 100) String password,
			@NotBlank @Size(min = 3, max = 50) String fullName, @NotBlank @Size(max = 50) @Email String email,
			@Size(min = 1) String[] roleIds) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.roleIds = roleIds;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}