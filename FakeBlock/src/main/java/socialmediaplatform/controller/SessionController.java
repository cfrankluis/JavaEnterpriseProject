package socialmediaplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialmediaplatform.model.User;
import socialmediaplatform.service.UserServiceImpl;



@RestController()
@RequestMapping(value = "/main")
public class SessionController {

	private UserServiceImpl myService;

	private SessionController() {
	}

	@Autowired
	private SessionController(UserServiceImpl myService) {
		super();
		this.myService = myService;
	}

	@GetMapping(value = "/getSession")
	public User getSession(HttpSession session) {
		User loggedInAccount = (User) session.getAttribute("loggedInAccount");
		if (loggedInAccount == null) {
			return new User();
		}
		return loggedInAccount;
	}

	@GetMapping(value = "/login")
	public String loginAttempt(HttpSession session, User userLogin) {

		User tryLogin = myService.getAccountByUserPass(userLogin.getUsername(), userLogin.getPassword());

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

		return "redirect: /index.html";
	}
}
