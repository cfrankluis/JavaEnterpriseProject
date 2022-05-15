
package application.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import application.model.User;
import application.service.UserService;

@Controller
@RequestMapping
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService service) {
		this.userService = service;
	}
	

	@GetMapping("")
	public String showHomePage() {
		return "index.html";
	}

	@GetMapping(value = "/login")
	public String loginAttempt(HttpSession session,@RequestBody User userLogin) {

		User tryLogin = userService.getLogin(userLogin.getUsername(), userLogin.getPassword());

		if (tryLogin == null) {
			return "redirect: index.html";
		}

		session.setAttribute("loggedInAccount", tryLogin);

		return "redirect: home.html";
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
	@PostMapping(value = "/register1")
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
		user.setUserId(((User) session.getAttribute("loggedInAccount")).getUserId());
		System.out.println(user);

		User updatedUser = userService.updateUser(user);
		System.out.println(updatedUser);

		session.setAttribute("loggedInAccount", updatedUser);
		System.out.println(session.getAttribute("loggedInAccount"));
	}
	

	
	/**
	 * This method receives current session information, a MultipartFile object
	 * containing the uploaded image, and a model interface to add a message
	 * attribute to the return value. The file name of the uploaded item is saved as
	 * fileName and a message String is initialized. Within a try-catch block, a
	 * method from the S3Controller is called to upload the file to the S3 bucket
	 * which returns a string message which varies depending on the outcome. The
	 * given message String is added as an attribute to the "message" forward so
	 * that the user can see what happened.
	 * 
	 * @Author Dillon Meier
	 * @param
	 */
	@PostMapping("/upload")
	public String uploadProfilePic(HttpSession session, @RequestParam("file") MultipartFile multipart, Model model) {
		session.setAttribute("Session Id", 1);
		
		String fileName = multipart.getOriginalFilename();

		System.out.println("File name: " + fileName);

		String message = "";

		try {
			message = S3Controller.uploadPic("ProfilePic", fileName, multipart.getInputStream(), session);
		} 
		catch (Exception ex) {
			message = "Error uploading file: " + ex.getMessage();
		}
		
		model.addAttribute("message", message);
		return "message";
	}

	/**
	 * This method receives session information and returns a list of all
	 * users/friends excluding the logged in user.
	 * 
	 * @Author Dillon Meier
	 * @param session
	 * @return
	 */
	@PostMapping("/friends")
	public String getAllFriends(@RequestBody User user, Model model){
	//	User currentUser = (User) session.getAttribute("loggedInUser");
		List<User> list = userService.getAllUsers(user);
		model.addAttribute("friends", null);
		model.addAttribute("friends", list);
		return "friends";
	}
}
