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
