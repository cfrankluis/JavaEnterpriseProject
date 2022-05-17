package application.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import application.model.Confirmation;
import application.model.User;
import application.service.ConfirmationService;
import application.service.EmailService;
import application.service.UserService;

@RestController
public class RegesterController {

	private UserService userService;
	private ConfirmationService confirmation;
	private EmailService emailService;
	private ConfirmationService confirmationService;

	@Autowired
	public RegesterController(UserService userService, ConfirmationService confirmation, EmailService emailService,
			ConfirmationService confirmationService) {
		this.userService = userService;
		this.confirmation = confirmation;
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

	@PostMapping(value = "/regester1")
	public void sendEmailLink(HttpSession session) {
		User newUser = (User) session.getAttribute("loggedInAccount");
		Confirmation confirmationToken = new Confirmation(newUser);
		confirmationService.createConfirmationToken(confirmationToken);

		String toEmail = newUser.getEmail();

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("socialmedianow63@gmail.com");
		mailMessage.setText("To confirm your account, please click here : "
				+ "http://localhost:9022/confirm-account/?token=" + confirmationToken.getConfirmationToken());

		emailService.sendEmail(mailMessage);
	}

	@RequestMapping(value = "/confirm-account/*", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {

		Confirmation token = confirmationService.findByConfirmationToken(confirmationToken);

		if (token != null) {
			User user = userService.getUserByEmail(token.getUser().getEmail());
			System.out.println(user);
			user.setConfirmed(true);
			System.out.println(user.isConfirmed());
			userService.createUser(user);

//            modelAndView.setViewName("index.html");
			System.out.println("it worked");

			// set conformationtoken to null
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}

	@PostMapping("/forgotPassword")
	public @ResponseBody String forgotPassword(HttpSession session, @RequestBody User currentUser) {
		String userEmail = currentUser.getEmail();
		if (!userEmail.isEmpty()) {
			User user = userService.getUserByEmail(userEmail);
			if (user != null) {
				Confirmation confirmationToken = new Confirmation(user);

				confirmationService.createConfirmationToken(confirmationToken);

				SimpleMailMessage mailMessage = new SimpleMailMessage();

				mailMessage.setTo(userEmail);
				mailMessage.setSubject("Password reset link!");
				mailMessage.setText("To reset your password, please click here : "
						+ "http://localhost:9022/reset-password/?token=" + confirmationToken.getConfirmationToken()
						+ "\n\n your username is: " + user.getUsername());

				emailService.sendEmail(mailMessage);
				return "A password reset link has been emailed to you";
			}
			return "Email could not be found";
		} 
		else {
			return "Email Address field can not be empty.";
		}
	}
	
	@PostMapping(value = "/reset-password/", consumes = "application/json")
	public @ResponseBody String processResetPassword(HttpSession session, @RequestBody User jsonUser) {
		System.out.println(jsonUser);
	    String password = jsonUser.getPassword();
	    String username = jsonUser.getUsername();
	    String message;
	    User user = userService.getUserByUsername(username);
	     
	    if (user == null) {
	    	message = "Username did not match any records";
	        return "message";
	    } else {   
	    	user.setPassword(password);
	    	userService.updateUser(user);
	    message = "Password Sucessfully Reset!";
	    	return message;
	    }
	}
	
}