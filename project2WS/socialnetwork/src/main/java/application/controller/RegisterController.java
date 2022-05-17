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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import application.model.Confirmation;
import application.model.User;
import application.service.ConfirmationService;
import application.service.EmailService;
import application.service.UserService;

@RestController
public class RegisterController {
	
	private UserService userService;
	private EmailService emailService;
	private ConfirmationService confirmationService;
	
	
	@Autowired
	public RegisterController(UserService userService, EmailService emailService, ConfirmationService confirmationService ) {
		this.userService = userService;
		this.emailService = emailService;
		this.confirmationService = confirmationService;
	}
	
//	@GetMapping(value="/register")
//	public ModelAndView displayRegistration(HttpSession session, User user)
//	{
//		session.setAttribute("user", user);
//		session.setViewName("register");
//		return session;
//	}
//	
	@PostMapping(value="/register")
	public void sendEmailLink(@RequestBody User sentUser) {
		Confirmation confirmationToken = new Confirmation(sentUser);
		confirmationService.createConfirmationToken(confirmationToken);
		
		String toEmail = sentUser.getEmail();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
        +"http://localhost:9022/confirm-account/?token="+confirmationToken.getConfirmationToken());

        emailService.sendEmail(mailMessage);
	}	

	
	@GetMapping(value = "/confirm-account/*", params= {"token"} )
	public void confirmUserAccount( String token, HttpSession session, HttpServletResponse response) throws IOException {
		System.out.println(token);
		Confirmation theToken = confirmationService.findByConfirmationToken(token);
		User user = userService.getUserByEmail(theToken.getUser().getEmail());
	    session.setAttribute("token", theToken);
	    if (user == null) {
	    	session.setAttribute("message", "Invalid Token");
	    }
	    response.sendRedirect("http://localhost:9022/html/welcome.html");// send them to the html page. that will reference the "processResetPassword" method
	}
	
}