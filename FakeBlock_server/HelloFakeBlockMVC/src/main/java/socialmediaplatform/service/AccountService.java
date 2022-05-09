package socialmediaplatform.service;

import java.util.ArrayList;

import socialmediaplatform.model.Account;

public interface AccountService {
		// CRUD
	
		// Create
		public void makeAccount(Account account);
		// Retrieve
		public ArrayList<Account> getAllAccounts();
		public Account getAccountByUserPass(String username, String password);
		public Account getAccountById(int id);
		// Update
		public void newPassword(int id, String password);
		public void forgotPassword(int id, String password);
		// Delete
		public void removeAccount(int id);
}
