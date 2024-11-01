package com.eventManagement.resource;


import java.util.ArrayList; 
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eventManagement.Exception.UserSaveFailedException;
import com.eventManagement.dto.CommonApiResponse;
import com.eventManagement.dto.RegisterRequestDto;
import com.eventManagement.dto.UserDto;
import com.eventManagement.dto.UserLoginRequest;
import com.eventManagement.dto.UserLoginResponse;
import com.eventManagement.dto.UserResponseDto;
import com.eventManagement.dto.UserStatusUpdateRequestDto;
import com.eventManagement.entity.Address;
import com.eventManagement.entity.User;
import com.eventManagement.service.AddressService;
import com.eventManagement.service.UserService;
import com.eventManagement.utility.Constant.ActiveStatus;
import com.eventManagement.utility.Constant.UserRole;
import com.eventManagement.utility.jwtutils;


@Component
public class UserResource {
	

	private final Logger LOG = LoggerFactory.getLogger(UserResource.class);

	
	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@Autowired
	private jwtutils jwt;
	
	
	
	public ResponseEntity<CommonApiResponse> registerAdmin(RegisterRequestDto registerRequest) {

		LOG.info("Request received for Register Admin");
		
		CommonApiResponse commonApiResponse = new CommonApiResponse();
		
		if(registerRequest.getEmail() == null || registerRequest.getPassword() == null) {
			commonApiResponse.setResponseMessage("not valid give email");
			commonApiResponse.setSuccess(false);
			
			return new ResponseEntity<CommonApiResponse>(commonApiResponse,HttpStatus.BAD_REQUEST);
		}
		
		User userexist = userService.getUserByEmailAndStatus(registerRequest.getEmail(), ActiveStatus.ACTIVE.value());
		
		if(userexist != null) {
			commonApiResponse.setResponseMessage("user exist");
			commonApiResponse.setSuccess(false);
		
			return new ResponseEntity<CommonApiResponse>(commonApiResponse,HttpStatus.BAD_REQUEST);
		}
		
		
		
		User user = RegisterRequestDto.toUserEntity(registerRequest);
		
		user.setRole(UserRole.ROLE_ADMIN.value());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setStatus(ActiveStatus.ACTIVE.value());
		
// 		User userexist = userService.getUserByEmailAndStatus(registerRequest.getEmail(), ActiveStatus.ACTIVE.value());		
//		here what we do is first we check the user you want to register is already 
//		in the database or not if that is null then we add the user to the database or register the user
//		in the below line we add the user to the database
		
		userexist= userService.addUser(user);
		
		commonApiResponse.setResponseMessage("registered");
		commonApiResponse.setSuccess(true);
		
		return new ResponseEntity<CommonApiResponse>(commonApiResponse,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	public ResponseEntity<CommonApiResponse> registerUser(RegisterRequestDto registerRequest) {

		LOG.info("Request received for Register Admin");
		
		CommonApiResponse commonApiResponse = new CommonApiResponse();
		
		LOG.info(registerRequest.getEmail());
		
		if(registerRequest.getEmail() == null || registerRequest.getPassword() == null) {
			commonApiResponse.setResponseMessage("not valid give email");
			commonApiResponse.setSuccess(false);
			
			return new ResponseEntity<CommonApiResponse>(commonApiResponse,HttpStatus.BAD_REQUEST);
		}
		
		User userexist = userService.getUserByEmailAndStatus(registerRequest.getEmail(), ActiveStatus.ACTIVE.value());
		
		if(userexist != null) {
			commonApiResponse.setResponseMessage("user exist");
			commonApiResponse.setSuccess(false);
		
			return new ResponseEntity<CommonApiResponse>(commonApiResponse,HttpStatus.BAD_REQUEST);
		}
		
		if (registerRequest.getRole() == null) {
			commonApiResponse.setResponseMessage("bad request ,Role is missing");
			commonApiResponse.setSuccess(false);
			
			return new ResponseEntity<CommonApiResponse>(commonApiResponse, HttpStatus.BAD_REQUEST);
		}
		
		
		
		User user = RegisterRequestDto.toUserEntity(registerRequest);
		

		String encodedPassword = passwordEncoder.encode(user.getPassword());

		user.setStatus(ActiveStatus.ACTIVE.value());
		user.setPassword(encodedPassword);

		Address address = new Address();
		address.setCity(registerRequest.getCity());
		address.setPinCode(registerRequest.getPincode());
		address.setStreet(registerRequest.getStreet());

		Address savedAddress = this.addressService.addAddress(address);
		
		user.setAddress(savedAddress);
		
		
		
		userexist= userService.addUser(user);
		
		commonApiResponse.setResponseMessage("registered");
		commonApiResponse.setSuccess(true);
		
		return new ResponseEntity<CommonApiResponse>(commonApiResponse,HttpStatus.OK);
	}
		
		
		
// 		User userexist = userService.getUserByEmailAndStatus(registerRequest.getEmail(), ActiveStatus.ACTIVE.value());		
//		here what we do is first we check the user you want to register is already 
//		in the database or not if that is null then we add the user to the database or register the user
//		in the below line we add the user to the database
		
		
	
	
	
	
	
		public ResponseEntity<UserLoginResponse> login(UserLoginRequest request){
			
			UserLoginResponse response = new UserLoginResponse();
		
			
			List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(request.getRole()));
			
			try {
				Authentication auth = authmanager.authenticate(
						new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			}
			
			catch (Exception e) {
				response.setResponseMessage("not login");
				response.setSuccess(false);
				return new ResponseEntity<UserLoginResponse>(response, HttpStatus.BAD_REQUEST);
			}

			
			String jwtToken = jwt.generateToken(request.getEmail());
			
			User user = userService.getUserByEmailAndRoleAndStatus(request.getEmail(), request.getRole(),
					ActiveStatus.ACTIVE.value());

			UserDto userDto = UserDto.toUserDtoEntity(user);

			// user is authenticated
			if (jwtToken != null) {
				response.setToken(jwtToken);
				response.setUser(userDto);
				response.setResponseMessage("Logged in sucessful");
				response.setSuccess(true);
				return new ResponseEntity<UserLoginResponse>(response, HttpStatus.OK);
			}

			else {
				response.setResponseMessage("Failed to login");
				response.setSuccess(false);
				return new ResponseEntity<UserLoginResponse>(response, HttpStatus.BAD_REQUEST);
			}

		}
		
		
		
		
		
		
		
		
		public ResponseEntity<UserResponseDto> fetchUserByRole(String role){
			UserResponseDto response = new UserResponseDto();
			
			if (role == null) {
				response.setResponseMessage("missing role");
				response.setSuccess(false);    
				return new ResponseEntity<UserResponseDto>(response, HttpStatus.BAD_REQUEST);
			}
			

			List<User> users = new ArrayList<>();

			users = userService.getUserByRoleAndStatus(role, ActiveStatus.ACTIVE.value());

			List<UserDto> userDtos = new ArrayList<>();

			for (User user : users) {

				UserDto dto = UserDto.toUserDtoEntity(user);
				userDtos.add(dto);

			}

			response.setUser(userDtos);
			response.setResponseMessage("User Fetched Successfully");
			response.setSuccess(true);

			return new ResponseEntity<UserResponseDto>(response, HttpStatus.OK);
			
		}
		
		
		
		

		
		
		public ResponseEntity<CommonApiResponse> updateUserStatus(UserStatusUpdateRequestDto request) throws UserSaveFailedException {

			LOG.info("Received request for updating the user status");

			CommonApiResponse response = new CommonApiResponse();

			if (request == null) {
				response.setResponseMessage("bad request, missing data");
				response.setSuccess(false);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			if (request.getUserId() == 0) {
				response.setResponseMessage("bad request, user id is missing");
				response.setSuccess(false);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			User user = null;
			user = this.userService.getUserById(request.getUserId());

			user.setStatus(request.getStatus());

			User updatedUser = this.userService.updateUser(user,request.getUserId());

			if (updatedUser == null) {
				throw new UserSaveFailedException("Failed to update the User status");
			}

			response.setResponseMessage("User " + request.getStatus() + " Successfully!!!");
			response.setSuccess(true);
			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

		}

		public ResponseEntity<UserResponseDto> getUserById(int userId) {

			UserResponseDto response = new UserResponseDto();

			if (userId == 0) {
				response.setResponseMessage("Invalid Input");
				response.setSuccess(false);
				return new ResponseEntity<UserResponseDto>(response, HttpStatus.BAD_REQUEST);
			}

			List<User> users = new ArrayList<>();

			User user = this.userService.getUserById(userId);
			users.add(user);

			if (users.isEmpty()) {
				response.setResponseMessage("No Users Found");
				response.setSuccess(false);
				return new ResponseEntity<UserResponseDto>(response, HttpStatus.OK);
			}

			List<UserDto> userDtos = new ArrayList<>();

			for (User u : users) {

				UserDto dto = UserDto.toUserDtoEntity(u);

				userDtos.add(dto);

			}

			response.setUser(userDtos);
			response.setResponseMessage("User Fetched Successfully");
			response.setSuccess(true);

			return new ResponseEntity<UserResponseDto>(response, HttpStatus.OK);
		}
		
		
		
		
		
		
		
		
	
}
