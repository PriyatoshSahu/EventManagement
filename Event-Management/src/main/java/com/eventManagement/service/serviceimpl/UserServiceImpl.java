package com.eventManagement.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventManagement.Repository.UserRepo;
import com.eventManagement.entity.User;
import com.eventManagement.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepo userrepo;
	
	@Override
	public User addUser(User user) {
		return userrepo.save(user);
	}

	@Override
	public User updateUser(User user, int userId) {
		return userrepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userrepo.findAll();
	}

	@Override
	public User getUserById(int id) {
		return userrepo.findById(id).get();
	}

	@Override
	public void deleteUserById(int id) {
		userrepo.deleteById(id);
	}

	@Override
	public User getUserByEmailAndStatus(String email, String status) {
		return userrepo.findUserByEmailAndStatus(email, status);
	}

	@Override
	public User getUserByEmailId(String email) {
		return userrepo.findUserByEmail(email);
	}

	@Override
	public List<User> getAllUserByRole(String role) {
		return userrepo.findUserByRole(role);
	}

	@Override
	public User getUserByEmailAndRoleAndStatus(String email, String role, String status) {
		return userrepo.findUserByEmailAndRoleAndStatus(email, role, status);
	}

	@Override
	public List<User> updateAllUser(List<User> user) {
		return userrepo.saveAll(user);
	}

	@Override
	public List<User> getUserByRoleAndStatus(String role, String status) {
		return userrepo.findUserByRoleAndStatus(role, status);
	}

}
