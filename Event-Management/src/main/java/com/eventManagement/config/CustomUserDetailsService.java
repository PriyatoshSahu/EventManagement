package com.eventManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.eventManagement.entity.User;
import com.eventManagement.service.UserService;
import com.eventManagement.utility.Constant.ActiveStatus;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userservice;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userservice.getUserByEmailAndStatus(username, ActiveStatus.ACTIVE.value());
		
		CustomUserDetails customuserdetails = new CustomUserDetails(user);
		
		return customuserdetails;
	}

}
