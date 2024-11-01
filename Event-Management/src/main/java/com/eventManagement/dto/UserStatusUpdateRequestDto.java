package com.eventManagement.dto;

public class UserStatusUpdateRequestDto {
	
	private int userId;
	
	private String status;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserStatusUpdateRequestDto(int userId, String status) {
		super();
		this.userId = userId;
		this.status = status;
	}

	public UserStatusUpdateRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
