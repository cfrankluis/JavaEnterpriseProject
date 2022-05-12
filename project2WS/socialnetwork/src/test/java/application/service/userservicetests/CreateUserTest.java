package application.service.userservicetests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.List;

import application.dao.UserDao;
import application.model.SecurityAnswer;
import application.model.User;
import application.service.UserService;
class CreateUserTest {

	UserService myService;
	UserDao myDao = mock(UserDao.class);
	
	@BeforeEach
	void setUp() throws Exception {
		myService = new UserService(myDao);
	}

	

	// both email and username are not already taken.
	@Test
	void createUserTest_1() {
		// Arrange
		List<SecurityAnswer> securityQuestions = null;
		User newUser = new User("Appa", "Aang", "Momo", "theGAang@biosnmail.com", "123", securityQuestions);
		when(myDao.findByEmail(newUser.getEmail())).thenReturn(null);
		when(myDao.findByUsername(newUser.getUsername())).thenReturn(null);
		when(myDao.save(newUser)).thenReturn(newUser);
		// Act
		User createdUser = myService.createUser(newUser);
		// Assert
		assertEquals(newUser, createdUser, "Are they the same?");
	}
	
	
	// email is already taken.
	@Test
	void createUserTest_2() {
		// Arrange
		List<SecurityAnswer> securityQuestions = null;
		User newUser = new User("Appa", "Aang", "Momo", "theGAang@biosnmail.com", "123", securityQuestions);
		when(myDao.findByEmail(newUser.getEmail())).thenReturn(newUser);
		when(myDao.findByUsername(newUser.getUsername())).thenReturn(null);
		when(myDao.save(newUser)).thenReturn(newUser);
		// Act
		User createdUser = myService.createUser(newUser);
		// Assert
		assertFalse(newUser.equals(createdUser));
	}
	
	
	// username is already taken.
	@Test
	void createUserTest_3() {
		// Arrange
		List<SecurityAnswer> securityQuestions = null;
		User newUser = new User("Appa", "Aang", "Momo", "theGAang@biosnmail.com", "123", securityQuestions);
		when(myDao.findByEmail(newUser.getEmail())).thenReturn(null);
		when(myDao.findByUsername(newUser.getUsername())).thenReturn(newUser);
		when(myDao.save(newUser)).thenReturn(newUser);
		// Act
		User createdUser = myService.createUser(newUser);
		// Assert
		assertFalse(newUser.equals(createdUser));
	}
}
