package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.SecurityQuestion;
import application.service.SecurityQuestionService;

@RestController
public class SecurityQuestionController {
	private SecurityQuestionService securityQuestionService;
	
	@Autowired
	public SecurityQuestionController(SecurityQuestionService securityQuestionService) {
		this.securityQuestionService = securityQuestionService;
	}
	
	@PostMapping(value="/seedquestions")
	public List<SecurityQuestion> loadQuestions() {
		return securityQuestionService.seedQuestions();
	}
}
