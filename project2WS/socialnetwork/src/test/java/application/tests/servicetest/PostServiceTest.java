package application.tests.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.dao.PostDao;
import application.model.Post;
import application.model.User;
import application.service.PostService;

class PostServiceTest {

	private PostService service;
	private PostDao mockDao;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		mockDao = mock(PostDao.class);
		service = new PostService(mockDao);
	}

	@Test
	void testGetAllPost() {
		//ARRANGE
		List<Post> testList = new ArrayList<>();
		testList.add(new Post());
		testList.add(new Post());
		testList.add(new Post());
		testList.add(new Post());
		
		List<Post> expectedList = new ArrayList<>();
		expectedList.addAll(testList);
		
		when(mockDao.findAll()).thenReturn(testList);
		//ACT
		List<Post> actualList = service.getAllPost();
		
		//ASSERT
		assertEquals(expectedList,actualList);
		verify(mockDao,times(1)).findAll();
	}

	@Test
	void testLikePost() {
		//ARRANGE
		Post testPost = new Post();
		
		User testUser = new User();
		testUser.setUserId(1);
		
		User testUser2 = new User();
		testUser2.setUserId(2);
		
		testPost.setLikers(new HashSet<User>());
		
		when(mockDao.save(testPost)).thenReturn(testPost);
		
		//ACT
		service.likePost(testPost, testUser);
		service.likePost(testPost, testUser2);
		
		//ASSERT
		Set<User> afterLike = testPost.getLikers();
		assertTrue(afterLike.contains(testUser));
		assertTrue(afterLike.contains(testUser2));
		assertEquals(afterLike.size(), 2);
		verify(mockDao, times(2)).save(testPost);
	}
	
	@Test
	void testUnlikePost() {
		//ARRANGE
		Post testPost = new Post();
		
		User testUser = new User();
		testUser.setUserId(1);
		
		User testUser2 = new User();
		testUser2.setUserId(1);
		
		testPost.setLikers(new HashSet<User>());
		
		when(mockDao.save(testPost)).thenReturn(testPost);
		
		//ACT
		service.likePost(testPost, testUser);
		service.likePost(testPost, testUser2);
		
		//ASSERT
		Set<User> afterLike = testPost.getLikers();
		assertTrue(afterLike.isEmpty());
		verify(mockDao, times(2)).save(testPost);
	}

	@Test
	void testGetPostById() {
		//ARRANGE
		int id = 1;
		
		Post testPost = new Post();
		testPost.setPostId(id);
		
		Post expectedPostOne = new Post();
		expectedPostOne.setPostId(id);
		
		when(mockDao.existsById(id)).thenReturn(true);
		when(mockDao.getById(id)).thenReturn(testPost);
		
		//ACT
		Post actualPostOne = service.getPostById(id);
		
		//ASSERT
		assertEquals(actualPostOne, expectedPostOne);
		verify(mockDao, times(1)).getById(id);
		verify(mockDao, times(1)).existsById(id);
	}
	
	@Test
	void testGetPostByIdNotFound() {
		//ARRANGE
		int id = 1;
		
		when(mockDao.existsById(id)).thenReturn(false);
		
		//ACT
		Post actualPostOne = service.getPostById(id);
		
		//ASSERT
		assertNull(actualPostOne);
		verify(mockDao, times(1)).existsById(id);
	}

}
