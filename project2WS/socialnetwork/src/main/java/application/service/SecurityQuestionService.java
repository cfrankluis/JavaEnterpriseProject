package application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.SecurityQuestionDao;
import application.model.SecurityQuestion;

@Service("securityQuestionService")
public class SecurityQuestionService {
	
	private SecurityQuestionDao dao;
	
	@Autowired
	public SecurityQuestionService(SecurityQuestionDao dao) {
		this.dao = dao;
	}
	
	/**
	 * Adds all the security questions to a lookup table and returns them when.
	 */
	public List<SecurityQuestion> seedQuestions() {
	
		//Create a list of security questions
		List<SecurityQuestion> questions = new ArrayList<>();
		questions.add(new SecurityQuestion("What is your middle name?"));
		questions.add(new SecurityQuestion("Who did you go to prom with?"));
		questions.add(new SecurityQuestion("What was the brand of your first car"));
		questions.add(new SecurityQuestion("What is your mother's maiden name?"));
		
		//Add List to the database
		if(dao.count() != questions.size()) {
			dao.deleteAll();
			return dao.saveAll(questions);
		}
		
		else {
			return questions;
		}
	}
		
	/**
	 * @param id
	 * @return
	 *  An entity from the security question table as <code>SecurtiyQuestion</code> object
	 */
	public SecurityQuestion getQuestionById(int id) {
		return dao.getById(id);
	}
}
