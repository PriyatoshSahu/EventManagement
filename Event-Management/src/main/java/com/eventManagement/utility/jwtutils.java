    package com.eventManagement.utility;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class jwtutils {
	
	private String jwtSecretKey= "5aB3r1P8mNqRvKgY7cZxW9jF2hT6fU0eL4dS1aC3bXwVzE6tR8yH0jM5nQ8pZ2k";
	
	public String extractUsernameString(String token) {
	    Claims claims = extractAllClaims(token);
	    return claims.getSubject();
	}
	
	public Date extractExpirationTime(String token) {
	    Claims claims = extractAllClaims(token);
	    return claims.getExpiration();
	}
	

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    
    

	private Key getSignKey() {
		byte[] keybytes= Decoders.BASE64.decode(jwtSecretKey);
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	
	public String generateToken(String username) {
		Map<String , Object> claims = new HashMap<String,Object>();
		return createToken(claims,username); 
	}

	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		final String userName = extractUsernameString(token);
		return( userName.equals(userDetails.getUsername()) && isTokenExpired(token));
		
	}
	
	
	
	public boolean isTokenExpired(String token) {
		return extractExpirationTime(token).before(new Date());
	}
	
}
