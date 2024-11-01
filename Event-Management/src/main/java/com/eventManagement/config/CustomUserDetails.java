package com.eventManagement.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eventManagement.entity.User;

public class CustomUserDetails implements UserDetails{

	private String email;
	private String password;
	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
	public CustomUserDetails(User user) {
		this.email=user.getEmail();
		this.password=user.getPassword();
		
		String [] roles = user.getRole().split(",");
		for(String rol : roles) {
			authorities.add(new SimpleGrantedAuthority(rol));
		}
	}
	
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
}
