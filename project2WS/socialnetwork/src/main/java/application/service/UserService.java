
package application.service;

import java.util.List;

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

	/**
	 * Method receives a User object to insert into the DB. Sending a User object
	 * that violates the user table's unique key constraints will throw an
	 * exception, so the Username and Email are checked against the existing Users
	 * in the table. If any account is returned then the account creation will
	 * abort, print a failed message to the console, and return null. If the
	 * creation is a success, then it returns the newly added user object.
	 * 
	 * @Author Dillon Meier
	 * @param
	 * @return A User object or null
	 */
	public User createUser(User user) {
		System.out.println("Attempting to create account: " + user);
		User user1 = dao.findByEmail(user.getEmail());
		User user2 = dao.findByUsername(user.getUsername());

		if (user1 == null && user2 == null) {
			return dao.save(user);
		} else {
			return null;
		}
	}

	/**
	 * Method receives a User object to update a row in the DB, and saves it to the
	 * row containing the corresponding User ID PK. The only time it should fail is
	 * if somehow a user managed to submit changes for another user.
	 * 
	 * @Author Dillon Meier
	 * @param
	 * @return User object
	 */
	public User updateUser(User user) {
		return dao.save(user); // If the primary keys do not match, an exception is thrown.
	}

	/**
	 * Method takes in the current user object and returns a list of all users
	 * except the specified user.
	 * 
	 * @Author Dillon Meier
	 * @param user
	 * @return
	 */
	public List<User> getAllUsers(User user) {
		List<User> allUsers = dao.findAll();
		allUsers.remove(user.getUserId() - 1);
		return allUsers;
	}

	public User getUserByEmail(String email) {
		return dao.findByEmail(email);
	}
	public User getUserByUsername(String email) {
		return dao.findByUsername(email);
	}

	public User getUserById(int id) {
		return dao.getById(id);
	}

	public User getLogin(String username, String password) {
		User uN = dao.findByUsername(username);
		String pW = uN.getPassword();
		if (pW.contentEquals(password)) {
			return uN;
		} else {
			return null;
		}

	}

}
