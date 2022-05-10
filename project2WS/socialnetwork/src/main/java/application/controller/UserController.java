package application.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.model.User;
import application.service.UserService;

@RestController
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService service) {
		this.userService = service;
	}
	
	@PostMapping(value="/login",produces = "application/json", consumes="application/json")
	public User login(@RequestBody User user) {
		User testUser = userService.getUserByEmail(user.getEmail());
		
		if(testUser == null || !testUser.getPassword().equals(user.getPassword())) {
			return null;
		}
		
		return testUser;
	}
	
	@PostMapping(value="/register")
	@ResponseStatus(code=HttpStatus.CREATED)
	public User register(@RequestBody User user) {
		
		User newUser = userService.createUser(user);
		
		
		return newUser;
	}
	
}