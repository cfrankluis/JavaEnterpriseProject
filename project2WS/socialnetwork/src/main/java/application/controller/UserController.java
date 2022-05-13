package application.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/test")
	public String testController(String name) {
		String outputString = userService.testMethod() + " " + name;
		System.out.println(outputString);
		return outputString;
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
	public User register() {
		User newUser = new User();
		newUser.setEmail("java@rev.net");
		newUser.setPassword("p455w0rd");
		newUser.setFirstName("Gil");
		newUser.setLastName("Thunder");
			
		return userService.createUser(newUser);
	}	
}