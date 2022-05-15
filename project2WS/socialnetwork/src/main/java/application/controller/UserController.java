package application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.model.SecurityAnswer;
import application.model.SecurityQuestion;
import application.model.User;
import application.service.UserService;

@RestController
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService service) {
		this.userService = service;
	}
	
	@GetMapping("/logintest")
	public void loginTest(HttpSession session) {
		Integer num = 100;
		session.setAttribute("testNum", num);
	}
}