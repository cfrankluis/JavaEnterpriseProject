package socialmediaplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialmediaplatform.model.Account;
import socialmediaplatform.service.AccountServiceImpl;

@RestController()
@RequestMapping(value="/main")
public class SessionController {

	private AccountServiceImpl myService;

	private SessionController() {
	}

	@Autowired
	private SessionController(AccountServiceImpl myService) {
		super();
		this.myService = myService;
	}

	@GetMapping(value = "/getSession")
	public Account getSession(HttpSession session) {
		Account loggedInAccount = (Account) session.getAttribute("loggedInAccount");
		if (loggedInAccount == null) {
			return new Account();
		}
		return loggedInAccount;
	}

	@GetMapping(value = "/login")
	public String loginAttempt(HttpSession session, Account userLogin) {

		Account tryLogin = myService.getAccountByUserPass(userLogin.getUsername(), userLogin.getPassword());

		if (tryLogin == null) {
			return "/Resource/HTML/LoginAttemptFailed.html";
		}

		session.setAttribute("loggedInAccount", tryLogin);

		return "/Resource/HTML/home.html";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest req) {

		HttpSession session = req.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		return "/index.html";
	}
}
