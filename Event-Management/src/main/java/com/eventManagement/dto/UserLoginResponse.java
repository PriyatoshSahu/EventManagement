package com.eventManagement.dto;

public class UserLoginResponse extends CommonApiResponse{

private UserDto user;
private String token;



public UserDto getUser() {
	return user;
}
public void setUser(UserDto user) {
	this.user = user;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
public UserLoginResponse(UserDto user, String token) {
	super();
	this.user = user;
	this.token = token;
}
public UserLoginResponse() {
	super();
	// TODO Auto-generated constructor stub
}



}
