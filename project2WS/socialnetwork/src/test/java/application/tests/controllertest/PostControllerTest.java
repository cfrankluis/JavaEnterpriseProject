package application.tests.controllertest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.controller.PostController;
import application.model.Comment;
import application.model.Post;
import application.service.PostService;

class PostControllerTest {
	
	PostService mockService;
	PostController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		mockService = mock(PostService.class);
		controller = new PostController(mockService);
	}

	@Test
	void testSendAllPost() {
		//ARRANGE
		List<Post> testList = new ArrayList<>();
		testList.add(new Post());
		testList.add(new Post());
		testList.add(new Post());
		testList.add(new Post());
		
		List<Post> expectedList = new ArrayList<>();
		expectedList.addAll(testList);
		
		when(mockService.getAllPost()).thenReturn(testList);
		
		//ACT
		List<Post> actualList = controller.sendAllPost();
		
		//ASSERT
		assertEquals(expectedList, actualList);
		verify(mockService, times(1)).getAllPost();
		
	}
	
	@Test
	void getCommentByPostTest() {
		//ARRANGE
		Post testPost = new Post();
		testPost.setPostId(1);
		
		List<Comment> comments = new ArrayList<>();
		comments.add(new Comment());
		comments.add(new Comment());
		
		List<Comment> expectedComments = new ArrayList<>();
		expectedComments.addAll(comments);
		
		testPost.setComments(comments);
		when(mockService.getPostById(1)).thenReturn(testPost);
		//ACT
		List<Comment> result = controller.getCommentByPost(testPost);
		
		//ASSERT
		assertEquals(expectedComments, result);	
	}
	
	

//	@Test
//	void testLikePost() {
//		
//	}

}
