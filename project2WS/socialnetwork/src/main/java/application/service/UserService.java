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
	
//	public UserService() {
//		this.dao = new UserDao();
//	}
	

	public User getLogin(String username, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		return user;
	}

	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		User user = new User();
		return user;
	}

	public User getUserByUserEmail(String email) {
		User user = new User();
		return user;
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
