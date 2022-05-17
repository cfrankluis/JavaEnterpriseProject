package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.SecurityAnswerDao;

@Service("securityAnswerService")
public class SecurityAnswerService {
	
	private SecurityAnswerDao dao;
	
	@Autowired
	public SecurityAnswerService(SecurityAnswerDao dao) {
		this.dao = dao;
	}
}
