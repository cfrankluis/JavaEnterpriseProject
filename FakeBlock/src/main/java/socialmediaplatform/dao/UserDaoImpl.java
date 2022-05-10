package socialmediaplatform.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import socialmediaplatform.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Override
	public void createAccount(User account) {
		System.out.println(account);

	}

	@Override
	public ArrayList<User> retrieveAllUsers() {
		ArrayList<User> myAccs = new ArrayList<User>();
		myAccs.add(new User());
		myAccs.add(new User());
		myAccs.add(new User());
		return myAccs;
	}

	@Override
	public User retrieveUserByUserPass(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User retrieveUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePassword(int id, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetPassword(int id, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllByAccountId(int id) {
		// TODO Auto-generated method stub

	}

}
