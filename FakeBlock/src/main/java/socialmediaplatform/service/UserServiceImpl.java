package socialmediaplatform.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialmediaplatform.dao.UserDao;
import socialmediaplatform.model.User;

@Service("accountService")
public class UserServiceImpl implements UserService {

	// Variables
	private UserDao myDao;
	public static int timesCalled = 0;

	// Constructors
	public UserServiceImpl() {
	}

	@Autowired
	public UserServiceImpl(UserDao newDao) {
		myDao = newDao;
	}

	// Initializer block
	{
		timesCalled++;
	}

	// Business logic
	@Override
	public void makeAccount(User account) {
		myDao.createAccount(account);

	}

	@Override
	public ArrayList<User> getAllAccounts() {
		return myDao.retrieveAllUsers();

	}

	@Override
	public User getAccountByUserPass(String username, String password) {
		return myDao.retrieveUserByUserPass(username, password);
	}

	@Override
	public User getAccountById(int id) {
		return myDao.retrieveUserById(id);
	}

	@Override
	public void newPassword(int id, String password) {
		myDao.updatePassword(id, password);

	}

	@Override
	public void forgotPassword(int id, String password) {
		myDao.resetPassword(id, password);

	}

	@Override
	public void removeAccount(int id) {
		myDao.deleteAllByAccountId(id);

	}

	// Getters and Setters
	public UserDao getMyDao() {
		return myDao;
	}

	@Autowired
	public void setMyDao(UserDao myDao) {
		this.myDao = myDao;
	}

}
