package application.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import application.service.PostService;

@RestController
public class PostController {
	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping("/post")
	public String uploadPost(HttpSession session, String description, @RequestParam("file") MultipartFile multipart,
			Model model) {
		session.setAttribute("Session Id", 1);

		String fileName = multipart.getOriginalFilename();
		String imgUrl = "https://buckylebucket.s3.us-east-2.amazonaws.com/PostPics/"
				+ session.getAttribute("Session Id").toString() + "/" + fileName;

		System.out.println("Description: " + description);
		System.out.println("File name: " + fileName);

		String message = "";

		try {
			S3Controller.uploadPic("PostPic", fileName, multipart.getInputStream(), session);
			message = "Your file has been uploaded Successfully!";
			postService.createPost(session, description, imgUrl);
		} catch (Exception ex) {
			message = "Error uploading file: " + ex.getMessage();
		}

		model.addAttribute("message", message);
		return "message";

	}
}
