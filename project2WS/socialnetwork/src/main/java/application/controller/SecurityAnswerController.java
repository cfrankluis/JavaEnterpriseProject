package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import application.service.SecurityAnswerService;

@RestController
public class SecurityAnswerController {
	private SecurityAnswerService securityAnswerService;
	
	@Autowired
	public SecurityAnswerController(SecurityAnswerService securityAnswerService) {
		this.securityAnswerService = securityAnswerService;
	}
}
