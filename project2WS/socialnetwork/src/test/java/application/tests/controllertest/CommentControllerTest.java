package application.tests.controllertest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import application.model.Comment;
import application.service.CommentService;
import application.controller.CommentController;

class CommentControllerTest {

	CommentService mockService;
	CommentController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		mockService = mock(CommentService.class);
		controller = new CommentController(mockService);
	};

	@Test
	void createCommentTest() {
		//ARRANGE
		Comment testComment = new Comment();
		
		//ACT
		Comment actualComment = controller.makeComment(testComment);
		
		//ASSERT
		verify(mockService, times(1)).createComment(testComment);
	}

}
