package application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import application.model.User;
import application.service.UserService;

public class TestLogController {
	HttpSession mockSession;
	UserService mockUserSer;
	User mockUser;

	@BeforeEach
	void BeforeEach() {
		mockSession = Mockito.mock(HttpSession.class);
		mockUserSer = Mockito.mock(UserService.class);
		mockUser = Mockito.mock(User.class);
	}

	@Test
	void testLogin() {
		LogController testLogin = new LogController(mockUserSer);
		User testUser = new User();
////////////////////////////no user found
		///////// ARRANGE
		// throws to test not found.
		Mockito.when(mockUserSer.getLogin(Mockito.anyString(), Mockito.anyString())).thenThrow(NullPointerException.class);

		///////// ACT
		String result = testLogin.login(mockSession, mockUser);

		////////// ASSERT
		assertEquals("http://localhost:9001/(?)/incorrectcredentials", result);
		Mockito.verify(mockUser, Mockito.times(0)).getPage();
		Mockito.verify(mockUserSer, Mockito.times(1)).getLogin(mockUser.getUsername(), mockUser.getPassword());
		Mockito.verify(mockSession, Mockito.times(0)).setAttribute("user", mockUser);

/////////////////////////////// should work
		///////// ARRANGE
		// throws to test not found.
		Mockito.when(mockUserSer.getLogin(testUser.getUsername(), testUser.getPassword())).thenReturn(mockUser);
		Mockito.when(mockUser.getPage()).thenReturn("/it-worked");
		System.out.println(testUser);

		///////// ACT
		result = testLogin.login(mockSession, mockUser);

		////////// ASSERT
		assertEquals("/it-worked", result);
		Mockito.verify(mockUser, Mockito.times(1)).getPage();
		Mockito.verify(mockSession, Mockito.times(1)).setAttribute("user", mockUser);
	}

	@Test
	void testLogout() {
		///////// ARRANGE
		LogController testLogout = new LogController(mockUserSer);

		///////// ACT
		String result = testLogout.logout(mockSession);

		////////// ASSERT
		assertEquals("path to home screen", result);
		Mockito.verify(mockSession, Mockito.times(1)).invalidate();
	}

	
	
	@Test
	void testResetPassword() {
		LogController testReset = new LogController(mockUserSer);
		User testUser = new User();
		testUser.setEmail("test@test.com");
		testUser.setUsername("test");

//		System.out.println(testUser + " " + testUser.getEmail());
//		System.out.println(mockUser);

///////////////////////////////// email test
		///////// ARRANGE
		Mockito.when(mockUser.getEmail()).thenReturn("test@test.com");
		Mockito.when(mockUserSer.getUserByUsername("")).thenReturn(mockUser);

		///////// ACT
		String result = testReset.forgotPassword(mockSession, mockUser);

		////////// ASSERT
		assertEquals("it worked", result);
		Mockito.verify(mockUser, Mockito.times(1)).getEmail();

/////(!) fix after spring email is learned
/////////////////////////////// username test
		///////// ARRANGE
//		Mockito.when(mockUser.getEmail()).thenReturn(null);
//		Mockito.when(mockUser.getUsername()).thenReturn("test");
//		Mockito.when(mockUserSer.getUserByUsername("")).thenReturn(testUser);
//
//		///////// ACT
//		String result = testReset.forgetPassword(mockSession, mockUser);
//		
//		////////// ASSERT
//		assertEquals("test@test.com", result);
//		Mockito.verify(mockUser, Mockito.times(2)).getEmail();
//		Mockito.verify(mockUserSer, Mockito.times(1)).getUserByUsername("");
	}

}
