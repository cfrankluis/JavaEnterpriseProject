package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dao.UserDao;
import application.model.User;


@Service("userService")
public class UserService {
	private UserDao dao;
	
	@Autowired
	public UserService(UserDao dao) {
		this.dao = dao;
	}
	
	public String testMethod() {
		return "Hello Spring";
	}
	
	public User createUser(User user) {
		return dao.save(user);
	}
	
	public User getUserByEmail(String email) {
		return dao.findByEmail(email);
	}
	
}
