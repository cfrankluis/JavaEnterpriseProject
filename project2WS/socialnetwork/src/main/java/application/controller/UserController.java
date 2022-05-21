
package application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import application.dao.PostDao;
import application.model.Post;
import application.model.User;
import application.service.PostService;
import application.service.UserService;
import application.toolbox.Verification;

@Controller
@RequestMapping
public class UserController {
	private UserService userService;
	private PostService postService;

	@Autowired
	public UserController(UserService userService, PostService postService) {
		this.userService = userService;
		this.postService = postService;
	}

	@PostMapping(value = "/login")
	public String loginAttempt(HttpSession session, @RequestBody User user) {
		User tryLogin = userService.getLogin(user.getUsername(), user.getPassword());
		if (tryLogin.equals(null)) {
			return "redirect: /html/welcome.html";
		}
		session.setAttribute("loggedInAccount", tryLogin);
		System.out.println("Account signed in: " + tryLogin);
		return "redirect:/html/globalfeedpage.html";
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
	public @ResponseBody String register(HttpSession session, @RequestBody User user) {
		String message;
		if (user.getFirstName().isBlank() || user.getLastName().isBlank() || user.getEmail().isBlank()
				|| user.getUsername().isBlank() || user.getPassword().isBlank()) {
			message = "Field values cannot be blank.";
			return message;
		}
		if (!Verification.checkUserInput(user.getEmail())) {
			message = "Please enter a valid email address.";
			return message;
		}
		User newUser = userService.createUser(user);
		if (newUser != null) {
			session.setAttribute("loggedInAccount", newUser);
			message = "Account Creation Sucessfull!!!";
		} else {
			message = "Account Creation failed...";
		}
		return message;
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
	public String updateUser(HttpSession session, @RequestBody User user) {
		User temp = (User) session.getAttribute("loggedInAccount");
		String password = user.getPassword();
		String bio = user.getBio();
		if (password != null) {
			temp.setPassword(password);
		}
		if (bio != null) {
			temp.setBio(bio);
		}
		User updatedUser = userService.updateUser(temp);
		session.setAttribute("loggedInAccount", updatedUser);
		System.out.println(session.getAttribute("loggedInAccount"));
		return "redirect:/html/globalfeedpage.html";
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
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public @ResponseBody String uploadProfilePic(HttpSession session,
			@RequestBody @RequestParam("file") MultipartFile multipart) {
		String fileName = multipart.getOriginalFilename();
		System.out.println("File name: " + fileName);
		String message = "";
		try {
			message = S3Controller.uploadPic("ProfilePic", fileName, multipart.getInputStream(), session);
		} catch (Exception ex) {
			message = "Error uploading file: " + ex.getMessage();
		}
		System.out.println(message);
		return message;
	}

	/**
	 * returns the user that is currently logged in
	 * 
	 * @param session
	 * @return
	 * @Author Gibbons
	 */
	@PostMapping("/currentUser")
	public @ResponseBody User currentUser(HttpSession session) {
		User user = (User) session.getAttribute("loggedInAccount");
		
		List<Post> post = postService.getPostByAuthor(user);

		user.setPosts(post);

		return user;
	}
	
	/**
	 * returns the user that is currently logged in
	 * 
	 * @param Session
	 * @param String
	 * @return
	 * @Author Dillon Meier
	 */
	@PostMapping("/currentFriend")
	public @ResponseBody User currentFriend(HttpSession session, @RequestBody User user) {
		User friend = userService.getUserByUsername(user.getUsername());
		
		List<Post> post = postService.getPostByAuthor(friend);

		friend.setPosts(post);

		return friend;
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
	public @ResponseBody List<User> getAllFriends(@RequestBody User user) {
		List<User> newUser = userService.getAllUsers(user);
		return newUser;
	}

	@GetMapping("/logout")
	public String logOut(HttpServletRequest req) {
		User userSigningOut = (User) req.getSession().getAttribute("loggedInAccount");
		req.getSession().invalidate();
		System.out.println("User signed out: " + userSigningOut);
		return "redirect:/html/welcome.html";
	}
}
