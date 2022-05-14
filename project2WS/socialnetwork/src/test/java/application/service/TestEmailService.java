package application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import application.controller.LogController;
import application.model.User;

public class TestEmailService {
	
	
	
//	HttpSession mockSession;
//	UserService mockUserSer;
//	User mockUser;

	@BeforeEach
	void BeforeEach() {
//		mockSession = Mockito.mock(HttpSession.class);
//		mockUserSer = Mockito.mock(UserService.class);
//		mockUser = Mockito.mock(User.class);
	}

	@Test
	void testSendEmail() {
		
//		LogController testLogin = new LogController(mockUserSer);
//		User testUser = new User();
//////////////////////////////no user found
//		///////// ARRANGE
//		// throws to test not found.
//		Mockito.when(mockUserSer.getLogin(Mockito.anyString(), Mockito.anyString())).thenThrow(NullPointerException.class);
//
//		///////// ACT
//		String result = testLogin.login(mockSession, mockUser);
//
//		////////// ASSERT
//		assertEquals("http://localhost:9001/(?)/incorrectcredentials", result);
//		Mockito.verify(mockUser, Mockito.times(0)).getPage();
//		Mockito.verify(mockUserSer, Mockito.times(1)).getLogin(mockUser.getUsername(), mockUser.getPassword());
//		Mockito.verify(mockSession, Mockito.times(0)).setAttribute("user", mockUser);
		}
	
//	public void sendEmail(String toEmail, String subject, String body){

}
