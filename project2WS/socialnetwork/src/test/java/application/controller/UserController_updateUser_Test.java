package application.controller;

import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.SecurityAnswer;
import application.model.User;
import application.service.UserService;

class UserController_updateUser_Test {

	UserController userCont;
	UserService myService = mock(UserService.class);
	HttpSession mySesh = mock(HttpSession.class);

	@BeforeEach
	void setUp() throws Exception {
		userCont = new UserController(myService);
	}

	@Test
	void updateUserTest() {
		// Arrange
		List<SecurityAnswer> securityQuestions = null;
		User myUser = new User("Appa", "Aang", "Momo", "theGAang@biosnmail.com", "123", securityQuestions);
		when(((User) mySesh.getAttribute("loggedInAccount"))).thenReturn(myUser);
		when(myService.updateUser(myUser)).thenReturn(myUser);
		// Act
		userCont.updateUser(mySesh, myUser);
		// Assert
		verify(mySesh, times(2)).getAttribute("loggedInAccount");
	}

}
