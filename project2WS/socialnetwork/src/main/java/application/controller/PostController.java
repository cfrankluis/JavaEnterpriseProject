package application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/sessiontest")
	public String sessionTest(HttpServletRequest req) {
		Integer num = (Integer)req.getSession(false).getAttribute("testNum");
		if(num == null) {
			return "There is a session";
		} else {
			return "There is no session";
		}
	}
	
	
	//GET RID OF THIS METHOD ONCE WE CAN
	//GET A USER FROM A SESSION
	private User sampleUser() {
		User testUser = new User();
		testUser.setUserId(1);
		testUser.setEmail("email");
		testUser.setFirstName("firstname");
		testUser.setLastName("lastname");
		testUser.setPassword("password");
		testUser.setUsername("username");
		
		return testUser;
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
	 * Takes a post from the request body and returns the 
	 * list of comments associated with the post.
	 * @author Frank Carag
	 * @param post
	 * @return
	 */
	@GetMapping(value="/commentbypost", produces="application/json")
	public List<Comment> getCommentByPost(@RequestBody Post post){
		post = postService.getPostById(post.getPostId());
		return post.getComments();
	}
	
	/**
	 * Takes a post from the client and a user
	 * from the session. The user is added to a
	 * list in the post object that references the
	 * users who liked the post.
	 * @author Frank Carag
	 */
	@PostMapping(value="/postlike", consumes="application/json")
	public Post likePost(@RequestBody Post post) {
		/*
		 * JSON BODY FORMAT
		 * {
		 * 	"postId" : postId
		 * }
		 */		
		
		//REPLACE THIS USER OBJECT
		//Get User info from the session attribute
		User testUser = sampleUser();
		testUser.setUserId(1);
		
		//Get full information about the post
		post = postService.getPostById(post.getPostId());
		System.out.println(post.getLikers());
		return postService.likePost(post, testUser);
	}
	
	@PostMapping(value="/postlikeTwo", consumes="application/json")
	public Post likePostTwo(@RequestBody Post post) {
		/*
		 * JSON BODY FORMAT
		 * {
		 * 	"id" : postId
		 * }
		 */		
		
		//REPLACE THIS USER OBJECT
		//Get User info from the session attribute
		User testUser = sampleUser();
		testUser.setUserId(2);
		
		//Get full information about the post
		post = postService.getPostById(post.getPostId());
		System.out.println(post.getLikers());
		return postService.likePost(post, testUser);
	}
	
	
}