
package com.eventManagement.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonApiResponse {
	

	private String responseMessage;
	
	private boolean isSuccess;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public CommonApiResponse(String responseMessage, boolean isSuccess) {
		super();
		this.responseMessage = responseMessage;
		this.isSuccess = isSuccess;
	}
	
	public CommonApiResponse() {
		super();
	}
	
	
	
}
