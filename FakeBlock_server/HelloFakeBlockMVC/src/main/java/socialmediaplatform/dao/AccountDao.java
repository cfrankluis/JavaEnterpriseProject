package socialmediaplatform.dao;

import java.util.ArrayList;

import socialmediaplatform.model.Account;

public interface AccountDao {
	// CRUD
	
	// Create
	public void createAccount(Account account);
	// Retrieve
	public ArrayList<Account> retrieveAllAccounts();
	public Account retrieveAccountByUserPass(String username, String password);
	public Account retrieveAccountById(int id);
	// Update
	public void updatePassword(int id, String password);
	public void resetPassword(int id, String password);
	// Delete
	public void deleteAccount(int id);
}
