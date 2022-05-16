package application.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.Confirmation;
import application.model.User;
import application.service.ConfirmationService;
import application.service.EmailService;
import application.service.UserService;
import lombok.Data;

@Data
@RestController
@RequestMapping
public class LogController {
	
	UserService userService;
	EmailService email;
	ConfirmationService confirmationService;
	
	@Autowired
	public LogController(UserService userService, EmailService sendEmail, ConfirmationService confirmationService) {
		this.userService = userService;
		this.email = sendEmail;
		this.confirmationService = confirmationService;
	}
	
	
//	@PostMapping("/login/*")
//	public String login(HttpSession session, @RequestBody User sentUser) {
//
//		User currentUser;
//		String myPath = "start";
//		
//		currentUser = userService.getByUsername(sentUser.getUsername());
//
//
//		if (currentUser != null) {
////			System.out.println("it should work?");
//			session.setAttribute("user", currentUser);
//
//			// need logic to properly make the url.
//			myPath = currentUser.getPage();
//
//		} else {
//			myPath = "http://localhost:9001/(?)/incorrectcredentials";
//		}
//
//		return myPath;
//	}
//
//	@PostMapping("/logout/*")
//	public String logout(HttpSession session) {
//
//		session.invalidate();
//		return "path to home screen";
//	}


	@PostMapping("/forgotPassword")
	public void forgotPassword(HttpSession session, @RequestBody User currentUser) {
		System.out.println("in the reset");
		String userEmail = currentUser.getEmail(); 
		if (userEmail != null) {
			System.out.println("user: " + userEmail);
			User user = userService.getUserByEmail(userEmail);

			System.out.println("after user creation : " + user);
			System.out.println("here " + 0);
			Confirmation confirmationToken = new Confirmation(user);
			System.out.println("here " + 0.5);
			confirmationService.createConfirmationToken(confirmationToken);

			System.out.println("here " + 1);
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        
	        System.out.println("here " + 2);
	        mailMessage.setTo(userEmail);
	        mailMessage.setSubject("Password reset link!");
	        mailMessage.setText("To reset your password, please click here : "
	        +"http://localhost:9022/reset-password/?token="+confirmationToken.getConfirmationToken() 
	        + "\n\n your username is: " + user.getUsername());

	        
	        System.out.println("here " + 3);
	        email.sendEmail(mailMessage);

		} else {
			session.setAttribute("message", "no email found");//we can use this to get tiger stuff in JS
			System.out.println("end");
		}

	}


		@GetMapping(value = "/reset-password/*", params= {"token"} )
		public void showResetPasswordPage( String token, HttpSession session, HttpServletResponse response) throws IOException {
		    
			
			Confirmation theToken = confirmationService.findByConfirmationToken(token);
			User user = userService.getUserByEmail(theToken.getUser().getEmail());
		    session.setAttribute("token", theToken);
		    if (user == null) {
		    	session.setAttribute("message", "Invalid Token");
		    }
		    response.sendRedirect("http://localhost:9022/html/reset-password.html");// send them to the html page. that will reference the "processResetPassword" method
		}
		
		
		@PostMapping(value = "/reset-password/*", consumes = "application/json")
		public String processResetPassword(@RequestBody User jsonUser, HttpSession session) {
			System.out.println("in the reset password");
			System.out.println(jsonUser);
		    String password = jsonUser.getPassword();
		    String username = jsonUser.getUsername();
		    
		    
		    User user = userService.getUserByUsername(username);
		     
		    session.setAttribute("title", "Reset your password");
		    if (user == null) {
		    	session.setAttribute("message", "Invalid Token");
		        return "message";
		    } else {   
		    	user.setPassword(password);
		    	userService.updateUser(user);
		         
		        session.setAttribute("message", "You have successfully changed your password.");
		    }
		     
		    return "message";
		}

	}

