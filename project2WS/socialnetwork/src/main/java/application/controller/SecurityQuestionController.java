package application.controller;

import application.service.SecurityQuestionService;

public class SecurityQuestionController {
	private SecurityQuestionService securityQuestionService;
	
	public SecurityQuestionController(SecurityQuestionService securityQuestionService) {
		this.securityQuestionService = securityQuestionService;
	}
}
