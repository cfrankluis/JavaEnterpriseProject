package application.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import application.model.Comment;
import application.model.Post;
import application.model.User;
import application.service.PostService; 

@RestController
public class PostController {
	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
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
	public @ResponseBody String uploadPost(HttpSession session, @RequestBody String description, @RequestParam("file") MultipartFile multipart) {

		String fileName = multipart.getOriginalFilename();
		String imgUrl = "https://buckylebucket.s3.us-east-2.amazonaws.com/PostPics/"
				+ ((User)session.getAttribute("loggedInAccount")).getUserId() + "/" + fileName;

		System.out.println("Description: " + description);
		System.out.println("File name: " + fileName);

		String message = "";

		try {
			message = S3Controller.uploadPic("PostPic", fileName, multipart.getInputStream(), session);
				if(message.contentEquals("Your file has been uploaded Successfully!")) {
					postService.createPost(session, description, imgUrl);
					System.out.println(message);
				}
				else {
					message = "Post could not be uploaded: " + message;
					System.out.println(message);
				
				}
			
		} catch (Exception ex) {
			message = "Error uploading file: " + ex.getMessage();
			System.out.println(message);
		
		}

		System.out.println(message);
	
		return message;

	}

	
	/**
	 * Calls the <code>getAllPost</code> method
	 * from the <code>postService</code> object and
	 * sends the result to the client
	 * @author Frank Carag
	 */
	@GetMapping(value="/global", produces="application/json")
	public List<Post> sendAllPost(){
		return postService.getAllPost();
	}
	
	/**
	 * Takes a post from the client and a user
	 * from the session. The user is added to a
	 * list in the post object that references the
	 * users who liked the post.
	 * @author Frank Carag
	 */
	@PostMapping(value="/postlike", consumes="application/json")
	public Post likePost(HttpSession session,@RequestBody Post post) {
		/*
		 * JSON BODY FORMAT
		 * {
		 * 	"postId" : postId
		 * }
		 */		
		
		//REPLACE THIS USER OBJECT
		//Get User info from the session attribute
		User testUser = (User)session.getAttribute("loggedInAccount");
		
		//Get full information about the post
		post = postService.getPostById(post.getPostId());
		return postService.likePost(post, testUser);
	}
	
	@GetMapping(value="/commentbypost", produces="application/json")
	public List<Comment> getCommentByPost(int id){
		Post post = postService.getPostById(id);
		return post.getComments();
	}
	
}
