package application.service.userservicetests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import application.dao.UserDao;
import application.model.SecurityAnswer;
import application.model.User;
import application.service.UserService;

class UpdateUserTest {

	UserService myService;
	UserDao myDao = mock(UserDao.class);

	@BeforeEach
	void setUp() throws Exception {
		myService = new UserService(myDao);
	}

	@Test
	void updateUserTest_1() {
		// Arrange
		List<SecurityAnswer> securityQuestions = null;
		User updatedUser = new User(1, "Appa", "Aang", "Momo", "theGAang@biosnmail.com", "123", securityQuestions);
		when(myDao.save(updatedUser)).thenReturn(updatedUser);
		// Act
		User user = myService.updateUser(updatedUser);
		// Assert
		assertEquals(updatedUser, user, "They should match");
	}
}
