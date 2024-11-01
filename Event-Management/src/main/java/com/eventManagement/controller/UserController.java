package com.eventManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventManagement.Exception.UserSaveFailedException;
import com.eventManagement.dto.CommonApiResponse;
import com.eventManagement.dto.RegisterRequestDto;
import com.eventManagement.dto.UserLoginRequest;
import com.eventManagement.dto.UserLoginResponse;
import com.eventManagement.dto.UserResponseDto;
import com.eventManagement.dto.UserStatusUpdateRequestDto;
import com.eventManagement.resource.UserResource;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserResource userResource;
	
	
	@PostMapping("/admin/register")
	public ResponseEntity<CommonApiResponse> RegisterAdmin(@RequestBody RegisterRequestDto user){
		return userResource.registerAdmin(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<CommonApiResponse> RegisterUser(@RequestBody RegisterRequestDto user){
		return userResource.registerUser(user);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponse>login(@RequestBody UserLoginRequest request){
		return userResource.login(request);
	}
	
	@GetMapping("/fetch/user-role")
	public ResponseEntity<UserResponseDto> fetchAllUserByRole(@RequestParam("role") String role){
		return userResource.fetchUserByRole(role);
	}
	
	
	@PutMapping("/update/status")
	public ResponseEntity<CommonApiResponse> updateUserStatus(@RequestBody  UserStatusUpdateRequestDto update) throws UserSaveFailedException{
		return userResource.updateUserStatus(update);
	}
	
	
	@GetMapping("/fetch/user-id")
	public ResponseEntity<UserResponseDto> fetchUserById(@RequestParam("id") int id){
		return userResource.getUserById(id);
	}
	


}
