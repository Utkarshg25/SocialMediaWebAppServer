package controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.SignUpDao;
import dao.UserDao;
import ninja.Result;

public class SignUpControllerTest {

	@Mock
	private SignUpDao userdao;
	
	@InjectMocks
	private SignUpController signUpController;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetUser() {
		Result result = signUpController.getUser("bob@gmail.com");
		verify(userdao).getUserByName("bob@gmail.com");
		assertEquals(200,result.getStatusCode());
	}
	
	@Test
	public void testGetUserById() {
		Result result = signUpController.getUserById(1l);
		verify(userdao).getUserById(1l);
		assertEquals(200, result.getStatusCode());
	}
}
