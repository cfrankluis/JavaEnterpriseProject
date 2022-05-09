package socialmediaplatform.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialmediaplatform.dao.AccountDao;
import socialmediaplatform.dao.AccountDaoImpl;
import socialmediaplatform.model.Account;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	// Variables
	private AccountDao myDao;
	public static int timesCalled = 0;
	
	// Constructors
	public AccountServiceImpl() {
		myDao = new AccountDaoImpl();
	}
	@Autowired
	public AccountServiceImpl(AccountDao newDao) {
		myDao = newDao;
	}
	
	// Initializer block
	{
		timesCalled++;
	}
	
	// Business logic
	@Override
	public void makeAccount(Account account) {
		myDao.createAccount(account);

	}

	@Override
	public ArrayList<Account> getAllAccounts() {
		return myDao.retrieveAllAccounts();
		
	}

	@Override
	public Account getAccountByUserPass(String username, String password) {
		return myDao.retrieveAccountByUserPass(username, password);
	}

	@Override
	public Account getAccountById(int id) {
		return myDao.retrieveAccountById(id);
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
		myDao.deleteAccount(id);

	}

	// Getters and Setters
	public AccountDao getMyDao() {
		return myDao;
	}
	@Autowired
	public void setMyDao(AccountDao myDao) {
		this.myDao = myDao;
	}

	
	
}
