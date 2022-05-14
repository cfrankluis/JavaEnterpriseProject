package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import application.service.EmailService;

@SpringBootApplication
public class SocialnetworkApplication {

	
//	@Autowired
//	EmailService test;  
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(SocialnetworkApplication.class, args);
	}

//		@EventListener(ApplicationReadyEvent.class)
//		public void sendMail() {
//			test.sendEmail("socialmedianow63@gmail.com", "test", "did it work?");
//		}
//	
}
