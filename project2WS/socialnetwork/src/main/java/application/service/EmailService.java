package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import application.model.Confirmation;
import application.model.User;

@Service("EmailService")
public class EmailService {

	private JavaMailSender mailSender;
	private UserService userService;

	@Autowired
	public EmailService(JavaMailSender mailSender, UserService userService) {
		this.mailSender = mailSender;
		this.userService = userService;
	}

	public EmailService() {
	}

	public void sendEmail(String toEmail, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("socialmedianow63@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);
		System.out.println("email sent: " + subject);

	}

	
	public void sendEmail(SimpleMailMessage email) {
		email.setFrom("socialmedianow63@gmail.com");
		mailSender.send(email);
    }
	
	
}
