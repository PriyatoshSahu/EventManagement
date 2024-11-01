package com.eventManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eventManagement.entity.User;
import com.eventManagement.service.UserService;
import com.eventManagement.utility.Constant.ActiveStatus;
import com.eventManagement.utility.Constant.UserRole;

@SpringBootApplication
public class EventManagementApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(EventManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User admin = this.userService.getUserByEmailAndRoleAndStatus("demo.admin@demo.com",
				UserRole.ROLE_ADMIN.value(), ActiveStatus.ACTIVE.value());

		if (admin == null) {


			User user = new User();
			user.setEmail("demo.admin@demo.com");
			user.setPassword(passwordEncoder.encode("123456"));
			user.setRole(UserRole.ROLE_ADMIN.value());
			user.setStatus(ActiveStatus.ACTIVE.value());

			this.userService.addUser(user);

		}	
	}

}
