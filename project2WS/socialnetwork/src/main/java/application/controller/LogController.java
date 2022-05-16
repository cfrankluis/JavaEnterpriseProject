package application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Confirmation;
import application.model.User;
import application.service.ConfirmationService;
import application.service.EmailService;
import application.service.UserService;
import lombok.Data;

@Data
@Controller
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
		public String showResetPasswordPage( String token, HttpSession session) {
			
			Confirmation theToken = confirmationService.findByConfirmationToken(token);
			User user = userService.getUserByEmail(theToken.getUser().getEmail());
		    session.setAttribute("token", theToken);
		    if (user == null) {
		    	session.setAttribute("message", "Invalid Token");
		        return "message";
		    }
		    return "reset_password_page";// send them to the html page. that will reference the "processResetPassword" method
		}
		
		
		@PostMapping("/reset-password/*")
		public String processResetPassword(HttpServletRequest request, HttpSession session) {
		    String resetToken = request.getParameter("token");
		    String password = request.getParameter("password");
		    
		    Confirmation token = confirmationService.findByConfirmationToken(resetToken);
			User user = userService.getUserByEmail(token.getUser().getEmail());
		     
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

