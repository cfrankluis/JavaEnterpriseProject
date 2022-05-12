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
	
	@PostMapping("/like")
	public void likePost(@RequestBody Post post, @RequestBody User user) {
		postService.LikePost(post, user);
	}
}
