package com.example.onlinebookshop.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Validated
public class UserDTOString {
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
	@Size(max = 50)
    
	private String address;
	@Size(max = 50)
    
	private String phoneNumber;
	private Boolean gender;
	private byte[] photo;
	private String roles ;
	
	public UserDTOString() {
		// TODO Auto-generated constructor stub
	}

	public UserDTOString(String username, @Size(min = 4, max = 100) String password,
			@NotBlank @Size(min = 3, max = 50) String fullName, @NotBlank @Size(max = 50) @Email String email,
			@Size(max = 50) String address, @Size(max = 50) String phoneNumber, Boolean gender, byte[] photo,
			String roles) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.photo = photo;
		this.roles = roles;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	
}