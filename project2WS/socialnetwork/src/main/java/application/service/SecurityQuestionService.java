package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.SecurityQuestionDao;

@Service("securityQuestionService")
public class SecurityQuestionService {
	private SecurityQuestionDao dao;
	
	public SecurityQuestionService(SecurityQuestionDao dao) {
		this.dao = dao;
	}
}
