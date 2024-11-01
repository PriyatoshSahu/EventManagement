package com.eventManagement.dto;

import java.math.BigDecimal; 

import org.springframework.beans.BeanUtils;

import com.eventManagement.entity.Address;
import com.eventManagement.entity.User;

public class UserDto {

	
//	The UserDto class serves as a Data Transfer Object. DTOs are used to encapsulate data 
//	and transfer it between different layers of an application (e.g., from the backend to the frontend
	
	
//	The toUserDtoEntity method converts a User entity object (usually from the database) into a UserDto.
//	This conversion is useful because it allows you to filter and control what information gets exposed, ensuring that sensitive data is not unintentionally shared.

//	Encapsulation: Limits the data exposure to only what is needed, maintaining security.
//	Simplicity: Makes it easier to work with data on the client side, as unnecessary fields are excluded.
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private Address address;
	private String satus;
	private UserDto seller;
	private BigDecimal walletAmount;
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getSatus() {
		return satus;
	}
	public void setSatus(String satus) {
		this.satus = satus;
	}
	public UserDto getSeller() {
		return seller;
	}
	public void setSeller(UserDto seller) {
		this.seller = seller;
	}
	public BigDecimal getWalletAmount() {
		return walletAmount;
	}
	public void setWalletAmount(BigDecimal walletAmount) {
		this.walletAmount = walletAmount;
	}
	
	
	
	public UserDto(int id, String firstName, String lastName, String email, String password, String role,
			Address address, String satus, UserDto seller, BigDecimal walletAmount) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;
		this.satus = satus;
		this.seller = seller;
		this.walletAmount = walletAmount;
	}
	
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static UserDto toUserDtoEntity(User user) {
		UserDto userDto =new UserDto();
		BeanUtils.copyProperties(user, userDto, "seller");		
		return userDto;
	}

	
	
}
