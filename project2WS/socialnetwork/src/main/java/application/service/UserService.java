package application.service;

import java.util.ArrayList;

import application.model.User;

public interface UserService {
		// CRUD
	
		// Create
		public void makeAccount(User account);
		// Retrieve
		public ArrayList<User> getAllAccounts();
		public User getAccountByUserPass(String username, String password);
		public User getAccountById(int id);
		// Update
		public void newPassword(int id, String password);
		public void forgotPassword(int id, String password);
		// Delete
		public void removeAccount(int id);
}
