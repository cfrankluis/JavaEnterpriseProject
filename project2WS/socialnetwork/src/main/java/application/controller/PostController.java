package application.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import application.model.Post;
import application.model.User;
import application.service.PostService;
import lombok.Data;


@Data
@Controller
public class PostController {
	
	PostService postSer;
//	User user;
//	Post post;
	
	@Autowired
	public PostController(PostService postSer) {
		this.postSer = postSer;
//		this.user = user;
//		this.post = post;
	}
	
	private static String profanityStop (String check) {
//		if (email == null)
//			return false;
//
//		String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
//				+ "A-Z]{2,7}$";
//
//		Pattern pat = Pattern.compile(emailFormat);
//
		return "";
	}

	
	@GetMapping("/post")
	public void makePost(HttpSession session, @RequestBody Post post) {
		String cleanedPost = profanityStop(post.getPostBody());
		
		User user = (User) session.getAttribute("user");
		post = new Post(cleanedPost, user.getId() );
		
		
		
		postSer.createPost(post);
	}
	
	

}
