package socialmediaplatform.dao;

import java.util.ArrayList;

import socialmediaplatform.model.User;



public interface UserDao {
	// CRUD

	// Create
	public void createAccount(User account);

	// Retrieve
	public ArrayList<User> retrieveAllUsers();

	public User retrieveUserByUserPass(String username, String password);

	public User retrieveUserById(int id);

	// Update
	public void updatePassword(int id, String password);

	public void resetPassword(int id, String password);

	// Delete
	public void deleteAllByAccountId(int Id);
}
