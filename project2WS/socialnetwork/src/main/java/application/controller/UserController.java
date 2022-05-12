package application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.model.SecurityAnswer;
import application.model.SecurityQuestion;
import application.model.User;
import application.service.UserService;

@RestController
@RequestMapping
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService service) {
		this.userService = service;
	}

	@GetMapping("/test")
	public User testUser() {
		User testUser = new User();
		int id = 1;
		String firstName = "Zeke";
		String lastName = "Yaeger";
		String email = "beast@titan.com";
		String password = "Eren";

		// Security Questions
		SecurityQuestion q1 = new SecurityQuestion(1, "What is your name?");
		SecurityQuestion q2 = new SecurityQuestion(2, "Who's your father?");

		SecurityAnswer a1 = new SecurityAnswer(testUser, q1, "Zeke");
		SecurityAnswer a2 = new SecurityAnswer(testUser, q2, "Grisha");
		List<SecurityAnswer> securityQuestions = new ArrayList<>();
		securityQuestions.add(a1);
		securityQuestions.add(a2);

		testUser = new User(firstName, lastName, lastName, email, password, securityQuestions);
		return userService.createUser(testUser);
	}

	@PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
	public User login(@RequestBody User user) {
		User testUser = userService.getUserByEmail(user.getEmail());

		if (testUser == null || !testUser.getPassword().equals(user.getPassword())) {
			return null;
		}

		return testUser;
	}

	/**
	 * This method receives a User object in the form of a JSON which is parsed
	 * while being abstracted. The User object input is sent to the service layer
	 * and the return value is stored as newUser. If newUser contains a user object,
	 * then the user details are set to a session attribute and the actual user is
	 * redirected to the home page.
	 * 
	 * @Author Dillon Meier
	 * @return URI redirect String
	 */
	@PostMapping(value = "/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String register(HttpSession session, @RequestBody User user) {

		User newUser = userService.createUser(user);

		if (newUser != null) {
			session.setAttribute("loggedInAccount", newUser);
			return "redirect: /home.html";
		} else {
			return "redirect: /index.html";
		}
	}

	/**
	 * This method receives a User object and updates the userId with the Id of the
	 * currently logged in User via session attributes. The User object is then sent
	 * to the service layer which returns the same object after updating the row in
	 * the DB matching the UserId column. The new User details then overwrite the
	 * session attribute "loggedInAccount".
	 * 
	 * @Author Dillon Meier
	 * @Return void
	 */
	@PostMapping(value = "/updateUserDetails")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void updateUser(HttpSession session, @RequestBody User user) {
		user.setId(((User) session.getAttribute("loggedInAccount")).getId());
		System.out.println(user);

		User updatedUser = userService.updateUser(user);
		System.out.println(updatedUser);

		session.setAttribute("loggedInAccount", updatedUser);
		System.out.println(session.getAttribute("loggedInAccount"));
	}
}