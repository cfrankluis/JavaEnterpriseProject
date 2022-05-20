package application.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import application.dao.PostDao;
import application.model.User;
import application.service.PostService;

@Controller
@RequestMapping
public class PageController {
	private PostService postService;

	@Autowired
	public PageController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("")
	public String showHomePage() {
		return "/html/welcome.html";
	}

	@GetMapping("/globalfeedpage")
	public String showBlock() {
		return "/html/globalfeedpage.html";
	}

	@GetMapping("/profilepage")
	public String greeting() {
		return "/html/profilepage.html";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPassword() {
		return "/html/forgot-password.html";
	}
	
	@GetMapping("/reset-password/*")
	public String resetPassword() {
		return "/html/reset-password.html";
	}
	@GetMapping("/post")
	public String posted() {
		System.out.println("in get /post");
		return "/html/globalfeedpage.html";
	}
	
	/**
	 * This method receives current session information, a description String, a
	 * MultipartFile object containing the uploaded image, and a model interface to
	 * add a message attribute to the return value. The file name of the uploaded
	 * item is saved as fileName, an imgUrl string is filled with the URL of the
	 * image once uploaded, and a message String is initialized. Within a try-catch
	 * block, a method from the S3Controller is called to upload the file to the S3
	 * bucket which returns a string message which varies depending on the outcome.
	 * The given message String is added as an attribute to the "message" forward so
	 * that the user can see what happened.
	 * 
	 * @Author Dillon Meier
	 * @param
	 */
	@PostMapping("/post")
	public String uploadPost(HttpSession session, String description, @RequestParam("file") MultipartFile multipart) {
		System.out.println("In upload post / page Controller method");
		User user = (User)session.getAttribute("loggedInAccount");
		int i = user.getUserId();

		String fileName = multipart.getOriginalFilename();
		String imgUrl = "https://buckylebucket.s3.us-east-2.amazonaws.com/PostPics/"
				+ i + "/" + fileName;

		System.out.println("Description: " + description);
		System.out.println("File name: " + fileName);

		String message = "";

		try {
			message = S3Controller.uploadPic("PostPic", fileName, multipart.getInputStream(), session);
				if(message.contentEquals("Your file has been uploaded Successfully!")) {
					postService.createPost(session, description, imgUrl);
					System.out.println(message + "in try if");
				}
				else {
					message = "Post could not be uploaded: " + message;
					System.out.println(message + "in try else");
				}
		} catch (Exception ex) {
			message = "Error uploading file: " + ex.getMessage();
			System.out.println(message + "in catch");
		}
		return "redirect:/html/globalfeedpage.html";
	}
}
