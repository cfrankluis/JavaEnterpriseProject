package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.modle.User;
import com.service.UserService;

import lombok.Data;

@Data
@Controller
public class LogController {
	
	UserService userSer;
	
	// no arg and all arg Autowire
//	@Autowire
//	public (!)
	
	
	@PostMapping("/login/*")
	public String login(HttpSession session, @RequestBody User currentUser) {

		String myPath;

//		userSer = new UserService();

		try {
			userSer.getLogin(currentUser.getUsername(), currentUser.getPassword());
		} catch (Exception e) {
			currentUser = null;
		}

		if (currentUser != null) {
			session.setAttribute("user", currentUser);

			// need logic to properly make the url.
			myPath = currentUser.getPage();

		} else {
			myPath = "http://localhost:9001/(?)/incorrectcredentials";
		}

		return myPath;
	}

	@PostMapping("/logout/*")
	public String logout(HttpSession session) {

		// does this work if we are not logged in? use if session does not = null then nothing
		session.invalidate();
		return "path to home screen";
	}

	// not exactly sure how to send an email change return type when we learn,
	// probably void ad call emailing method below.
	public String forgetPassword(HttpSession session, @RequestBody User currentUser) {

//		System.out.println(currentUser);
		if (currentUser.equals(null)) {
			return "No account found";
		} else if (currentUser.getEmail() != null) {
			// this of call a method and give it the email
			return currentUser.getEmail();// spring email

		} else if (currentUser.getUsername() != null) {
			userSer = new UserService();
//			System.out.println(currentUser.getUsername());
			// could switch this out for if statement. if we make an account checker. 
			try {
				currentUser = userSer.getUserByUsername(currentUser.getUsername());
			} catch (Exception e) {
				return "No account found";
			}
//			System.out.println(currentUser+ " " + currentUser.getEmail());
			// this of call a method and give it the email
			return currentUser.getEmail();
		} else {
			return "No account found";
		}

	}

}
