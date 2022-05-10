package application.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.User;
import application.service.UserService;
import application.service.UserServiceImpl;

@RestController
@RequestMapping(value="/account")
public class UserController {
	
	private UserService myService;
	
	private UserController() {
	}
	
	@Autowired
	private UserController(UserServiceImpl myService) {
		super();
		this.myService = myService;
	}
	
	// returns JSON
	@GetMapping(value="/getallaccounts")
	public ArrayList<User> giveMeAccounts() {
		return myService.getAllAccounts();
	}
	// accepts JSONs
	@PostMapping(value = "/newaccount")
	public void newAccount(User newestAccount) {
		myService.makeAccount(newestAccount);
	}
	
	@GetMapping(value="/loadaccount")
	public User loadAccountById(int id) {
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
