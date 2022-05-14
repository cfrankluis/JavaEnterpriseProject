package com.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.modle.User;
import com.service.UserService;

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
		LogController testLogin = new LogController();

/////////////////////////////// should work
		///////// ARRANGE
		// throws to test not found.
		Mockito.when(mockUserSer.getLogin("", "")).thenReturn(mockUser);
		Mockito.when(mockUser.getPage()).thenReturn("/it-wored");

		///////// ACT

		////////// ASSERT
		assertEquals("/it-wored", testLogin.login(mockSession, mockUser));
		Mockito.verify(mockUser, Mockito.times(1)).getPage();
		Mockito.verify(mockUserSer, Mockito.times(1)).getLogin("", "");
		Mockito.verify(mockSession, Mockito.times(1)).setAttribute("", mockUser);

//////////////////////////// no user input
		///////// ARRANGE
		// return null to test bad log in.
		Mockito.when(mockUserSer.getLogin("", "")).thenReturn(mockUser);
		Mockito.when(mockUser.getPage()).thenReturn("/it-wored");

		///////// ACT

		////////// ASSERT
		assertEquals("http://localhost:9001/(?)/incorrectcredentials", testLogin.login(mockSession, mockUser));
		Mockito.verify(mockUser, Mockito.times(0)).getPage();
		Mockito.verify(mockUserSer, Mockito.times(1)).getLogin("", "");
		Mockito.verify(mockSession, Mockito.times(0)).setAttribute("", mockUser);

//////////////////////////// no user found
		///////// ARRANGE
		// throws to test not found.
		Mockito.when(mockUserSer.getLogin("", "")).thenThrow(Exception.class);
		Mockito.when(mockUser.getPage()).thenReturn("/it-wored");

		///////// ACT

		////////// ASSERT
		assertEquals("http://localhost:9001/(?)/incorrectcredentials", testLogin.login(mockSession, mockUser));
		Mockito.verify(mockUser, Mockito.times(0)).getPage();
		Mockito.verify(mockUserSer, Mockito.times(1)).getLogin("", "");
		Mockito.verify(mockSession, Mockito.times(0)).setAttribute("", mockUser);
	}

	@Test
	void testLogout() {
		///////// ARRANGE
		LogController testLogout = new LogController();

		///////// ACT
//		String logedOut =testLogout.logout(mockSession);

		////////// ASSERT
		assertEquals("path to home screen", testLogout.logout(mockSession));
		Mockito.verify(mockSession, Mockito.times(1)).invalidate();
	}

	@Test
	void testResetPassword() {
		LogController testReset = new LogController();
		User testUser = new User();
		testUser.setEmail("test@test.com");
		testUser.setUsername("test");

		System.out.println(testUser + " " + testUser.getEmail());
		System.out.println(mockUser);
/////(!) not working
/////////////////////////////// username test
		///////// ARRANGE
//		Mockito.when(mockUser.getEmail()).thenReturn(null);
//		Mockito.when(mockUser.getUsername()).thenReturn("test");
//		Mockito.when(mockUserSer.getUserByUsername("")).thenReturn(testUser);
//
//		///////// ACT
//
//		////////// ASSERT
//		assertEquals("test@test.com", testReset.resetPassword(mockSession, mockUser));
//		Mockito.verify(mockUser, Mockito.times(2)).getEmail();
//		Mockito.verify(mockUserSer, Mockito.times(1)).getUserByUsername("");

///////////////////////////////// email test
		///////// ARRANGE
		Mockito.when(mockUser.getEmail()).thenReturn("test@test.com");
		Mockito.when(mockUserSer.getUserByUsername("")).thenReturn(mockUser);

		///////// ACT

		////////// ASSERT
		assertEquals("test@test.com", testReset.forgetPassword(mockSession, mockUser));
		Mockito.verify(mockUser, Mockito.times(2)).getEmail();
	}

}
