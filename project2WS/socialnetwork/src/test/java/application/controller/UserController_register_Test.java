package application.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.SecurityAnswer;
import application.model.User;
import application.service.UserService;

class UserController_register_Test {

	UserController userCont;
	UserService myService = mock(UserService.class);
	HttpSession mySesh = mock(HttpSession.class);

	@BeforeEach
	void setUp() throws Exception {
		userCont = new UserController(myService);
	}

	@Test
	void registerTest_1() {
		// Arrange
		List<SecurityAnswer> securityQuestions = null;
		User myUser = new User("Appa", "Aang", "Momo", "theGAang@biosnmail.com", "123", securityQuestions);
		when(myService.createUser(myUser)).thenReturn(myUser);
		String expectedUri = "redirect: /home.html";
		// Act
		String uri = userCont.register(mySesh, myUser);
		// Assert
		assertEquals(expectedUri, uri, "Did it redirect to the correct URI?");
	}

	@Test
	void registerTest_2() {
		// Arrange
		List<SecurityAnswer> securityQuestions = null;
		User myUser = new User("Appa", "Aang", "Momo", "theGAang@biosnmail.com", "123", securityQuestions);
		when(myService.createUser(myUser)).thenReturn(null);
		String expectedUri = "redirect: /index.html";
		// Act
		String uri = userCont.register(mySesh, myUser);
		// Assert
		assertEquals(expectedUri, uri, "Did it redirect to the correct URI?");
	}
}
