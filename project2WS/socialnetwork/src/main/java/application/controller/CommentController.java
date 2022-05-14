package application.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.Comment;
import application.model.User;

import application.service.CommentService;

@RestController
public class CommentController {
	private CommentService commentService;
	
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
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
	
	@PostMapping(value="/comment", consumes="application/json")
	public Comment makeComment(@RequestBody Comment comment) {
		/*
		 * JSON FORMAT
		 * {
		 * "content": "test comment",
		 * "post" : {"id" : 1}
		 * }
		 */
		User testUser = sampleUser();
		
		comment.setAuthor(testUser);
		
		return commentService.createComment(comment);
	}
	
	@PostMapping(value="/commlike", consumes="application/json")
	public Comment likeComment(@RequestBody Comment comment) {
		/*
		 * JSON BODY FORMAT
		 * {
		 * 	"id" : commentId
		 * }
		 */		
		
		//REPLACE THIS USER OBJECT
		//Get User info from the session attribute
		User testUser = sampleUser();
		
		comment = commentService.getCommentById(comment.getId());
		if(comment != null)
			return commentService.LikeComment(comment, testUser);
		else
			return null;
	}

}
