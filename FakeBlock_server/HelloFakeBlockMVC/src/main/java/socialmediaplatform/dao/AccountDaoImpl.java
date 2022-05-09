package socialmediaplatform.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import socialmediaplatform.model.Account;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

	@Override
	public void createAccount(Account account) {
		System.out.println(account);

	}

	@Override
	public ArrayList<Account> retrieveAllAccounts() {
		ArrayList<Account> myAccs = new ArrayList<Account>();
		myAccs.add(new Account());
		myAccs.add(new Account());
		myAccs.add(new Account());
		return myAccs;
	}

	@Override
	public Account retrieveAccountByUserPass(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account retrieveAccountById(int id) {
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
	public void deleteAccount(int id) {
		// TODO Auto-generated method stub

	}

}
