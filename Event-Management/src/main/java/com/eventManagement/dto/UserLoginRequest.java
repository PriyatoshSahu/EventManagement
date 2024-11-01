package com.eventManagement.dto;

public class UserLoginRequest {

	private String email;
	private String password;
	private String role;
	
	
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserLoginRequest(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public UserLoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
