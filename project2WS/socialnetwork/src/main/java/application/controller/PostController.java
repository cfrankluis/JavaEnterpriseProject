package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/global")
	public List<Post> sendAllPost(){
		return postService.getAllPost();
	}
	
	@PostMapping(value="/like", consumes="application/json")
	public void likePost(@RequestBody Post post) {
		//Get User info from the session attribute
		User testUser = new User();
		testUser.setId(1);
		testUser.setEmail("email");
		testUser.setFirstName("firstname");
		testUser.setLastName("lastname");
		testUser.setPassword("password");
		testUser.setUsername("username");
		//Get full information about the post
		post = postService.getPostById(post.getId());
		
		postService.LikePost(post, testUser);
	}
}
