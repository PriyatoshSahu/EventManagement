//package com.eventManagement.security;
//
//
//
//import org.springframework.context.annotation.Bean;  
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.hotelrents.Security.Jwt.AuthTokenFilter;
//import com.hotelrents.Security.Jwt.JwtEntryPoint;
//import com.hotelrents.Security.usseerr.HotelUserDetailsService;
//
//@Configuration
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
//
// This @EnableMethodSecurity annotation indicate or inform to tyhe spring that fdont go for the default flow 
// go with the flow which i mentioned here in the class
//
// By default the security is go for the Security Filter Chain so thats why we nee dto customise it and give our own flow
//
//
//
// To verify the username, password we are using userDetailsService 
// 
//public class EventSecurityConfig {
//
//	 private final HotelUserDetailsService userDetailsService;
//	 
//	 private final JwtEntryPoint jwtAuthEntryPoint;
//
//	    public HotelSecurityConfig(HotelUserDetailsService userDetailsService, JwtEntryPoint jwtAuthEntryPoint) {			
//			this.userDetailsService = userDetailsService;
//			this.jwtAuthEntryPoint = jwtAuthEntryPoint;
//		}
//
//		@Bean
//	    public AuthTokenFilter authenticationTokenFilter(){
//	        return new AuthTokenFilter();
//	    }
//		
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	    
//	    When ever we passs the username and password in the login form that object server receive that san basically 
//	    authentication object which not authenticated at all it first goes to the AuthenticationProvider it will basically provide 
//	    the service of checking it and validating it and makes a Authenticated object so this is bydefault but i want my own Authentication Provider so we 
//	    	   
//      AuthenticationProvider uses the UserDetailsService for validating user from database but as per we want our 
//	    own AuthenticationProvider so we need to give our own HotelUserDetailsService object .
//	    in my case its HotelUserDetailService because UserDetailsService is implenet by the HotelUserDetailsService
//	    and we need to pass the or set the UserDetailsService in he AuthenticationProvider 
//	    authProvider.setUserDetailsService(userDetailsService); like this 
//	    
//	    @Bean
//	    public DaoAuthenticationProvider authenticationProvider() {
//	        var authProvider = new DaoAuthenticationProvider();
//	        authProvider.setUserDetailsService(userDetailsService);
//	        authProvider.setPasswordEncoder(passwordEncoder());
//	        return authProvider;
//	    }
//
//	    AuthenticationConfiguration class have a method to get the object of the AuthenticationManager Interface
//	    thats why we passed the AuthenticationConfiguration class's object as parameter in the below method
//	    @Bean
//	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//	        return authConfig.getAuthenticationManager();
//	    }
//
//	    
//	    It will give you the object of securityfilterchain
//	    the method build() will give you the object of securityfilterchain
// 	    
//	    @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        http.csrf(AbstractHttpConfigurer :: disable)
//	                .exceptionHandling(
//	                        exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
//	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	                .authorizeHttpRequests(auth -> auth
//	                		.requestMatchers("/auth/**", "/rooms/**","/booking/**")
//	                        .permitAll()
//	                        .requestMatchers("/roles/**").hasRole("ADMIN")
//	                        .anyRequest().authenticated());
//	        http.authenticationProvider(authenticationProvider());
//	        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//	        return http.build();
//	    }
//	}
//
//
