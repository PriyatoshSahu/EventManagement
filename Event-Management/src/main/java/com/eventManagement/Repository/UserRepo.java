package com.eventManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventManagement.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	User findUserByEmail(String email);
	
	User findUserByEmailAndStatus(String email,String status);
	
	List<User> findUserByRole(String role);
	
	User findUserByEmailAndRoleAndStatus(String email,String role,String status);
	
	List<User> findUserByRoleAndStatus(String role,String status);
	
//	boolean existsByEmailAndStatus(String email, String status);
}
