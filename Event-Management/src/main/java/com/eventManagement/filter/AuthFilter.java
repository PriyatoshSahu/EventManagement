package com.eventManagement.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eventManagement.config.CustomUserDetailsService;
import com.eventManagement.utility.jwtutils;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter{

	@Autowired
	private jwtutils jwt;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
//			Here we just collect the token and store that in a string type 
			String jwtToken = parseJwtGetTheJwtToken(request);
			String userName= null;
			if(jwtToken != null  && jwtToken.startsWith("Bearer")) {
	    			
				userName = jwt.extractUsernameString(jwtToken);
				
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
				
				UsernamePasswordAuthenticationToken authenticationn=
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());			
				
				authenticationn.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationn);
			}
		}
		catch(Exception e) {
			System.out.println("cant authenticate");
		}
	filterChain.doFilter(request, response);
		
}
	
	
	
	
	
	
	
	
	
	
	private String parseJwtGetTheJwtToken(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization"); // the authheader have the jwt token so here we first get the auth header 
		
		
//		If the headerauth is not null and we know that the token is stored in headerauth and the token starts with Bearer so first we verify that then extract the substring token
		if (StringUtils.hasText(headerAuth)&& headerAuth!=null && headerAuth.startsWith("Bearer")) {
			return headerAuth.substring(7);
		}
			return null;
		}
}
