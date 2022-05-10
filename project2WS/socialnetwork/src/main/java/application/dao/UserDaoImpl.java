package application.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import application.model.User;



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
