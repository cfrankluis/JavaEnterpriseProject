package application.tests.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import application.model.Comment;
import application.model.Post;
import application.model.User;

import application.service.CommentService;
import application.dao.CommentDao;

class CommentServiceTest {
	
	CommentDao mockDao;
	CommentService service;
	
	@BeforeEach
	void setUp() throws Exception {
		mockDao = mock(CommentDao.class);
		service = new CommentService(mockDao);
		
	}

	@Test
	void testCreateComment() {
		//ARRANGE
		Comment testComment = new Comment();
		Post testPost = new Post();
		
		testComment.setPost(testPost);
		when(mockDao.save(testComment)).thenReturn(testComment);
		
		//ACT
		Comment addedComment = service.createComment(testComment);
		
		//ASSERT
		assertEquals(addedComment, testComment);
		verify(mockDao, times(1)).save(testComment);	
	}

	
	@Test
	void testGetCommentById() {
		//ARRANGE
		int id = 1;
		Comment testComment = new Comment();
		testComment.setId(id);
		
		Comment expectedComment = new Comment();
		expectedComment.setId(id);
		
		when(mockDao.existsById(id)).thenReturn(true);
		when(mockDao.getById(id)).thenReturn(testComment);
		
		//ACT
		Comment actualComment = service.getCommentById(id);
		
		//ASSERT
		assertEquals(expectedComment,actualComment);
		verify(mockDao, times(1)).getById(id);
		verify(mockDao, times(1)).existsById(id);
	}
	
	@Test
	void testGetCommentByIdNotFound() {
		//ARRANGE
		int id = 1;
		
		when(mockDao.existsById(id)).thenReturn(false);
		
		//ACT
		Comment actualComment = service.getCommentById(id);
		
		//ASSERT
		assertNull(actualComment);
		verify(mockDao, times(0)).getById(id);
		verify(mockDao, times(1)).existsById(id);
	}
	
	
	@Test
	void testLikeComment() {
		//ARRANGE
		Comment testComment = new Comment();
		testComment.setLikers(new HashSet<>());
		
		User testUser1 = new User();
		testUser1.setUserId(1);
		
		User testUser2 = new User();
		testUser2.setUserId(2);
		
		when(mockDao.save(testComment)).thenReturn(testComment);
		
		//ACT
		Comment result = service.LikeComment(testComment, testUser1);
		result = service.LikeComment(testComment, testUser2);
		
		//ASSERT
		Set<User> afterLike = result.getLikers();
		assertTrue(afterLike.contains(testUser1));
		assertTrue(afterLike.contains(testUser2));
	}
	
	@Test
	void testLikeCommentDuplicate() {
		//ARRANGE
		Comment testComment = new Comment();
		testComment.setLikers(new HashSet<>());
		
		User testUser1 = new User();
		testUser1.setUserId(1);
		
		User testUser2 = new User();
		testUser2.setUserId(1);
		
		when(mockDao.save(testComment)).thenReturn(testComment);
		
		//ACT
		Comment result = service.LikeComment(testComment, testUser1);
		result = service.LikeComment(testComment, testUser2);
		
		//ASSERT
		Set<User> afterLike = result.getLikers();
		assertFalse(afterLike.contains(testUser1));
		assertFalse(afterLike.contains(testUser2));
	}



}
