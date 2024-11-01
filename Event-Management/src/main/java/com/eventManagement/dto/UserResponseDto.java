package com.eventManagement.dto;

import java.util.ArrayList; 
import java.util.List;

public class UserResponseDto extends CommonApiResponse{

	
private List<UserDto> user = new ArrayList<>();



public List<UserDto> getUser() {
	return user;
}

public void setUser(List<UserDto> user) {
	this.user = user;
}

public UserResponseDto(String responseMessage, boolean isSuccess, List<UserDto> user) {
	super(responseMessage, isSuccess);
	this.user = user;
}

public UserResponseDto() {
	super();
	// TODO Auto-generated constructor stub
}

public UserResponseDto(String responseMessage, boolean isSuccess) {
	super(responseMessage, isSuccess);
	// TODO Auto-generated constructor stub
}



}
