package com.eventManagement.service;

import java.util.List;

import com.eventManagement.entity.User;

public interface UserService {
public User addUser(User user);
	
	public User updateUser(User user, int userId);
	
	public List<User> getAllUser();
	
	public User getUserById(int id);
	
	public void deleteUserById(int id);
	
	public User getUserByEmailId(String email);

	public User getUserByEmailAndStatus(String email, String status);
	
	List<User> getAllUserByRole(String role);
	
	User getUserByEmailAndRoleAndStatus(String email,String role,String status);
	
	List<User> updateAllUser(List<User> user);
	
	List<User> getUserByRoleAndStatus(String role,String status);
	
}
