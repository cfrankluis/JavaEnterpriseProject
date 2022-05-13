package application.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import application.model.User;
import application.service.UserService;
import lombok.Data;

@Data
@Controller
public class LogController {
	
	UserService userSer;
	
	@Autowired
	public LogController(UserService userSer) {
		this.userSer = userSer;
	}
	
	
	@PostMapping("/login/*")
	public String login(HttpSession session, @RequestBody User sentUser) {

		User currentUser;
		String myPath = "start";
//		System.out.println("in login " + sentUser);
		
		currentUser = userSer.getLogin(sentUser.getUsername(), sentUser.getPassword());


//		System.out.println("curent user: " + currentUser);


		if (currentUser != null) {
//			System.out.println("it should work?");
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
	@PostMapping("/forgotPassword/*")
	public String forgotPassword(HttpSession session, @RequestBody User currentUser) {

		if (currentUser.equals(null)) {
			return "No account found";
		} else if (currentUser.getEmail() != null) {
			// this of call a method and give it the email
			return "it worked";// spring email

		} else if (currentUser.getUsername() != null) {
//			System.out.println(currentUser.getUsername());
			// could switch this out for if statement. if we make an account checker. 
			try {
				currentUser = userSer.getUserByUsername(currentUser.getUsername());
			} catch (Exception e) {
				return "No account found";
			}
//			System.out.println(currentUser+ " " + currentUser.getEmail());
			// this of call a method and give it the email
			return "it worked"; // spring email
		} else {
			return "No account found";
		}

	}

}
