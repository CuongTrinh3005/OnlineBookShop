package com.example.onlinebookshop.payload.request;

import javax.validation.constraints.Size;

public class ChangePasswordRequest {
	@Size(min=3, max = 40)
	private String username;
	@Size(min=4, max = 40)
	private String currentPassword;
	@Size(min=4, max = 40)
	private String newPassword;
	
	public ChangePasswordRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public ChangePasswordRequest(@Size(min = 3, max = 40) String username, @Size(min = 4, max = 40) String currentPassword,
			@Size(min = 4, max = 40) String newPassword) {
		super();
		this.username = username;
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	
}
