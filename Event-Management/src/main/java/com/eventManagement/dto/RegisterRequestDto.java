package com.eventManagement.dto;

import org.springframework.beans.BeanUtils;

import com.eventManagement.entity.User;

import jakarta.validation.constraints.Email;

//RegisterRequestDto acts as a Data Transfer Object, which is a design pattern used to transfer data
//between layers of an application [1].


// By using a DTO, you separate the data model that is exposed to the client from the actual entity model that your
//application uses internally. This approach allows you to keep your internal database structure hidden from the client, 
//and you can control which fields are exposed.
//For example, the User entity might contain additional fields like id, createdAt, updatedAt, or passwordHash
//which should not be directly controlled by the client or sent over the network.

public class RegisterRequestDto {

		private String firstName;

		private String lastName;

		@Email
		private String email;

		private String password;

		private String phone;

		private String role;

		private String street;

		private String city;

		private int pincode;
		
		
		
		
		

//      The BeanUtils.copyProperties method is used to copy properties from one object to another. 
//		It simplifies the process of mapping data from a DTO (Data Transfer Object) to an entity class by automatically
//		transferring the values of matching fields, avoiding the need to set each field manually.

		
		
//		The toEntity method converts a DTO to an entity object. This is useful because, in many applications, 
//		data is transferred in the form of DTOs (from requests), but it needs to be stored in a structured format 
//		(entities) in the database. Writing a separate method to handle this conversion ensures consistency and reduces 
//		redundancy, making it easier to maintain and change code when needed.
		
		
		
		
		
		
		
		
		
		
		
		
		public RegisterRequestDto() {
			super();
			// TODO Auto-generated constructor stub
		}






		public RegisterRequestDto(String firstName, String lastName, String email, String password, String phone,
				String role, String street, String city, int pincode) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.phone = phone;
			this.role = role;
			this.street = street;
			this.city = city;
			this.pincode = pincode;
		}






		public String getFirstName() {
			return firstName;
		}






		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}






		public String getLastName() {
			return lastName;
		}






		public void setLastName(String lastName) {
			this.lastName = lastName;
		}






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






		public String getPhone() {
			return phone;
		}






		public void setPhone(String phone) {
			this.phone = phone;
		}






		public String getRole() {
			return role;
		}






		public void setRole(String role) {
			this.role = role;
		}






		public String getStreet() {
			return street;
		}






		public void setStreet(String street) {
			this.street = street;
		}






		public String getCity() {
			return city;
		}






		public void setCity(String city) {
			this.city = city;
		}






		public int getPincode() {
			return pincode;
		}






		public void setPincode(int pincode) {
			this.pincode = pincode;
		}






		public static User toUserEntity(RegisterRequestDto registerRequestDto) {
			User user = new User();
			BeanUtils.copyProperties(registerRequestDto, user);
			return user;
		}

	}



