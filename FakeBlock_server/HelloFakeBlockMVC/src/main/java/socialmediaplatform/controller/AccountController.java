package socialmediaplatform.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialmediaplatform.model.Account;
import socialmediaplatform.service.AccountServiceImpl;

@RestController
@RequestMapping(value="/account")
public class AccountController {
	
	private AccountServiceImpl myService;
	
	private AccountController() {
	}
	
	@Autowired
	private AccountController(AccountServiceImpl myService) {
		super();
		this.myService = myService;
	}
	
	// returns JSON
	@GetMapping(value="/getallaccounts")
	public ArrayList<Account> giveMeAccounts() {
		return myService.getAllAccounts();
	}
	// accepts JSONs
	@PostMapping(value = "/newaccount")
	public void newAccount(Account newestAccount) {
		myService.makeAccount(newestAccount);
	}
	
	@GetMapping(value="/loadaccount")
	public Account loadAccountById(int id) {
		return myService.getAccountById(id);
	}
	
	@PutMapping(value="/changepassword")
	public void changePassword(@RequestBody int id, String newPassword) {
		myService.newPassword(id, newPassword);
	}
	
	@PutMapping(value="/resetpassword")
	public void resetPassword(int id, String newPassword) {
		myService.forgotPassword(id, newPassword);
	}
	
	@DeleteMapping(value="/deleteaccount")
	public void deleteAccount(int id) {
		myService.removeAccount(id);
	}
}
